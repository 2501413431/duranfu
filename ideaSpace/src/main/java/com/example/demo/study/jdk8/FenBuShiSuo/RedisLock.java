package com.example.demo.study.jdk8.FenBuShiSuo;

import redis.clients.jedis.Jedis;

import java.time.LocalTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2018/10/17
 * http://ifeve.com/redis-lock-2/
 */


class LockCase3 extends RedisLock3 {

	LockCase3(Jedis jedis, String name) {
		super(jedis, name);
	}

	@Override
	public void lock() {
		while (true) {
			String result = jedis.set(lockKey, lockValue, "NX", "EX", 30);//SET lockKey value NX EX 30
			if("OK".equals(result)) {
				System.out.println(Thread.currentThread().getId() + "加锁成功");
				//开启定时刷新过期时间
				isOpenExpirationRenewal = true;
				scheduleExpirationRenewal();
				break;
			}
			System.out.println("线程id:"+Thread.currentThread().getId() + "获取锁失败，休眠10秒!时间:"+ LocalTime.now());
			//休眠10秒
			sleepBySencond(10);
		}
	}

	@Override
	public void unlock() {
		String lockValue = jedis.get(lockKey);
		if (lockValue.equals(this.lockValue)) {
			jedis.del(lockKey);
			isOpenExpirationRenewal = false;
		}
	}

	/**
	 * 具有原子性的释放锁
	 */
	public void unlock1() {
		//使用lua脚本进行原子删除操作
		String checkAndDelScript = "if redis.call('get', KEYS[1]) == ARGV[1] then " +
				"return redis.call('del', KEYS[1]) " +
				"else " +
				"return 0 " +
				"end";
		jedis.eval(checkAndDelScript, 1, lockKey, lockValue);
	}
}

/**
 * 版本5-确保过期时间大于业务执行时间
 * 抽象类RedisLock增加boolean类型的属性isOpenExpirationRenewal，用来标识是否开启定时刷新过期时间。
 * 在增加一个scheduleExpirationRenewal方法用于开启刷新过期时间的线程。
 */
abstract class RedisLock3 implements Lock {
	protected Jedis jedis;

	protected String lockKey;

	protected String lockValue;

	protected volatile boolean isOpenExpirationRenewal = true;

	/**
	 * 开启定时刷新
	 */
	protected void scheduleExpirationRenewal() {
		Thread thread = new Thread(new ExpirationRenewal());
		thread.start();
	}

	/**
	 * 刷新key过期时间
	 */
	private class ExpirationRenewal implements  Runnable {
		@Override
		public void run() {
			while (isOpenExpirationRenewal) {
				System.out.println("执行延迟失效时间中。。。");
				String checkAndExpireScript = "if redis.call('get', KEYS[1]) == ARGV[1] then " +
						"return redis.call('expire',KEYS[1],ARGV[2]) " +
						"else " +
						"return 0 end";
				jedis.eval(checkAndExpireScript, 1, lockKey, lockValue, "30");
				//休眠10秒
				sleepBySencond(10);
			}

		}
	}

	public RedisLock3(Jedis jedis, String lockKey) {
		this(jedis, lockKey, UUID.randomUUID().toString()+Thread.currentThread().getId());
	}

	public RedisLock3(Jedis jedis, String lockKey, String lockValue) {
		this.jedis = jedis;
		this.lockKey = lockKey;
		this.lockValue = lockValue;
	}

	public void sleepBySencond(int sencond) {
		try {
			Thread.sleep(sencond*1000);
		} catch (InterruptedException e) {
		}
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {

	}

	@Override
	public Condition newCondition() {
		return null;
	}

	@Override
	public boolean tryLock() {
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return false;
	}
}



class LockCase2 extends RedisLock2 {

	LockCase2(Jedis jedis, String name) {
		super(jedis, name);
	}

	@Override
	public void lock() {
		while (true) {
			String result = jedis.set(lockKey, lockValue, "NX");//SET lockKey value NX
			if ("OK".equals(result)) {
				System.out.println(Thread.currentThread().getId() + "加锁成功");
				break;
			}
		}
	}

	@Override
	public void unlock() {
		String lockValue = jedis.get(lockKey);
		if (lockValue.equals(this.lockValue)) {
			jedis.del(lockKey);
		}
	}

	/**
	 * 具有原子性的释放锁
	 */
	public void unlock1() {
		//使用lua脚本进行原子删除操作
		String checkAndDelScript = "if redis.call('get', KEYS[1]) == ARGV[1] then " +
				"return redis.call('del', KEYS[1]) " +
				"else " +
				"return 0 " +
				"end";
		jedis.eval(checkAndDelScript, 1, lockKey, lockValue);
	}
}

/**
 * 版本3-设置锁的value
 * 抽象类RedisLock增加lockValue字段，lockValue字段的默认值为UUID随机值假设当前线程ID
 */
abstract class RedisLock2 implements Lock {
	protected Jedis jedis;

	protected String lockKey;

	protected String lockValue;

	public RedisLock2(Jedis jedis, String lockKey) {
		this(jedis, lockKey, UUID.randomUUID().toString()+Thread.currentThread().getId());
	}

	public RedisLock2(Jedis jedis, String lockKey, String lockValue) {
		this.jedis = jedis;
		this.lockKey = lockKey;
		this.lockValue = lockValue;
	}

	public void sleepBySencond(int sencond) {
		try {
			Thread.sleep(sencond*1000);
		} catch (InterruptedException e) {
		}
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {

	}

	@Override
	public Condition newCondition() {
		return null;
	}

	@Override
	public boolean tryLock() {
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return false;
	}
}


/**
 * 如果A获得了锁，突然断电，A会一直持有锁，其他客户端永远获取不到锁
 * 可以通过过期时间来解决这个问题如 lock1,但会被其他客户端误删
 * 这时会有两个问题：
 * 1、过期时间如何保证大于业务执行时间
 * 2、如何保证锁不被误删
 *
 */
class LockCase1 extends RedisLock {

	LockCase1(Jedis jedis, String name) {
		super(jedis, name);
	}

	@Override
	public void lock() {
		while (true) {
			String result = jedis.set(lockKey, "value", "NX");//SET lockKey value NX
			if ("OK".equals(result)) {
				System.out.println(Thread.currentThread().getId() + "加锁成功");
				break;
			}
		}
	}

	@Override
	public void unlock() {
		jedis.del(lockKey);
	}

	public void lock1() {
		while (true) {
			String result = jedis.set(lockKey, "value", "NX", "EX", 30);//SET lockKey value NX EX 30
			if("OK".equals(result)) {
				System.out.println(Thread.currentThread().getId() + "加锁成功");
				break;
			}
		}
	}
}

/**
 * redis锁的抽象类
 */
public abstract class RedisLock implements Lock {

	protected Jedis jedis;

	protected String lockKey;

	public RedisLock(Jedis jedis, String lockKey) {
		this.jedis = jedis;
		this.lockKey = lockKey;
	}

	public void sleepBySencond(int sencond) {
		try {
			Thread.sleep(sencond*1000);
		} catch (InterruptedException e) {
		}
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {

	}

	@Override
	public Condition newCondition() {
		return null;
	}

	@Override
	public boolean tryLock() {
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return false;
	}
}
