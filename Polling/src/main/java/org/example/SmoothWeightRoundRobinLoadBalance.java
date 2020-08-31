package org.example;

import org.example.pojo.Server;

import java.util.List;

/**
 * Created by caojidasabi on 2020/8/31.
 */
public class SmoothWeightRoundRobinLoadBalance extends AbstractLoadBalance{


    /**
     * 总权重
     */
    private Integer totalWeight =0;


    @Override
    protected void init() {
        super.init();

        totalWeight = getServers().stream().mapToInt(Server::getWeight).sum();


    }

    /*上面两种轮询的实现有一个很大的问题就是会造成某台权重较高的机器在短时间内负载较高，其实轮询我们希望的是，比如 A(1),B(2),C(3) 三台机器我们希望的是每 6 次调用，有 1 次是 A，2 次是 B，3 次是 C 即可。当然也可以进行改动，比如复制法可以将复制后的机器列表打乱。

    在 Nginx 中有一种平滑加权的轮询算法。在上面的其他权重算法中，每台机器会有一个权重 weight 属性，这个属性是配置好之后就不变的，在平滑加权轮询中每台机器还有一个当前权重的属性 currentWeight，这个属性是会变化的。比如 A(1),B(2),C(3) 三台机器，它们的初始化 currentWeight 都是 0，发生调用的时候，就会每台机器的 currentWeight += weight，然后选择所有机器中最大的 currentWeight 所在的机器（如果出现相同，则按照顺序选择第一个），随后这台机器的 currentWeight 减去总 weight 权重之和 totalWeight。

    这个算法中 currentWeight 总和是不变的，因为被选择的机器的 currentWeight 减去 totalWeight 后每台机器的 currentWeight 又会分别加上自身的 weight，即 weight 越大的机器被选择的次数就会越多。这个算法的数学证明可以参见：https://www.fanhaobai.com/2018/12/load-balance-smooth-weighted-round-robin.html
    */
    @Override
    protected Server doSelect() {



        List<Server> servers = getServers();

        if(servers  == null){
            return super.doSelect();
        }

        Integer maxCurrWeight = Integer.MIN_VALUE;

        Server maxCurrWeightServer = servers.get(0);

        for(Server server :servers){

            server.setCurrWeight(server.getCurrWeight() + server.getWeight());

            if(server.getCurrWeight() > maxCurrWeight){
                maxCurrWeight = server.getCurrWeight();
                maxCurrWeightServer = server;
            }

        }

        maxCurrWeightServer.setCurrWeight(maxCurrWeightServer.getCurrWeight() - totalWeight);

        System.out.println(maxCurrWeightServer);

        return maxCurrWeightServer;
    }
}
