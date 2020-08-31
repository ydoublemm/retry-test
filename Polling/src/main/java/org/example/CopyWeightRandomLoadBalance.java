package org.example;

import org.example.pojo.Server;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by caojidasabi on 2020/8/30.
 *
 * 加权随机
 */
public class CopyWeightRandomLoadBalance extends AbstractLoadBalance{


    @Override
    protected void init() {
        super.init();

        Map<Server, Integer> map = getServers().stream()
                .collect(
                        Collectors.toMap(Function.identity(),Server::getWeight)
                );

        List<Server> serverList = getServers();

        map.forEach((k,v)->{
            if(v>1){
               for(int i=0;i<v-1;i++){

                   serverList.add(k);

               }


            }

        });
    }


    @Override
    protected Server doSelect() {
        return getServers().get(new Random().nextInt(getServers().size()));
    }


}
