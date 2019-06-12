package com.example.demo.study.jdk8;

import java.util.HashMap;
import java.util.Map;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/5/11
 *
 *避免一连串的if  else
 * 策略模式 + 工厂模式 + 单例模式
 */
public class IfElse {
    public static void main(String[] args) {
        String result = ObtainStrategyInfo.getInstance().getStrategyInfo("策略一");
        System.out.println(result);
    }
}


class ObtainStrategyInfo {
    private static final ObtainStrategyInfo obtainStrategyInfo = new ObtainStrategyInfo();
    public static ObtainStrategyInfo getInstance(){
        return obtainStrategyInfo;
    }
    public String getStrategyInfo(String strategy){
        StrategyInfo strategyInfo = new StrategyFactory().getStrategyInfoClass(strategy);
        return strategyInfo.getStrategyInfo(strategy);
    }
}



/**工厂模式    最终if else的判断逻辑***/
class StrategyFactory {
    private static Map<String, StrategyInfo> strategyInfoMap = new HashMap<String, StrategyInfo>();
    static {
        strategyInfoMap.put("策略一", new Strategy1());
        strategyInfoMap.put("策略二", new Strategy2());
    }

    public StrategyInfo getStrategyInfoClass(String strategy) {
        StrategyInfo strategyInfo = null;
        for(String key : strategyInfoMap.keySet()){
            if(strategy.equals(key)) {
                strategyInfo = strategyInfoMap.get(key);
            }
        }
        return strategyInfo;
    }
}




/**策略模式**/
interface StrategyInfo {
    String getStrategyInfo(String strategy);
}

class Strategy1 implements StrategyInfo {
    @Override
    public String getStrategyInfo(String strategy) {
        return strategy;
    }
}

class Strategy2 implements StrategyInfo {
    @Override
    public String getStrategyInfo(String strategy) {
        return strategy;
    }
}
