package org.example;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.example.pojo.Server;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * Created by caojidasabi on 2020/8/25.
 */
@Data
public class AbstractLoadBalance {

    protected List<Server> servers;



    protected final Multiset<Server> results = HashMultiset.create();

    public Server selectHost(){
        List<Server> list = getServers();
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        if (list.size() == 1) {
            return list.get(0);
        }
        return doSelect();
    }

    public Server selectHost(Object client){
        List<Server> list = getServers();
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        if (list.size() == 1) {
            return list.get(0);
        }
        return doSelect(client);
    }

    protected Server doSelect(){
        return null;
    }

    protected Server doSelect(Object client){
        return null;
    }

    //todo 优化refesh
    protected boolean addServer(Server server){
        if (getServers() != null){
            return servers.add(server);
        }
        return false;
    }

    //todo 优化refesh
    protected boolean removeServer(Server server){
        if (getServers() != null){
            return servers.remove(server);
        }
        return false;
    }

    //todo 不要返回集合
    protected List<Server> getServers(){
        return servers;
    }

    public void result(int loop) {

        if(CollectionUtils.isEmpty(servers)){
            init();
        }

        results.clear();
        if (loop < 1) {
            throw new IllegalArgumentException();
        }
        IntStream.range(0, loop).forEach(i -> results.add(selectHost()));
        Set<Multiset.Entry<Server>> entrySet = results.entrySet();
        entrySet.stream().sorted(Comparator.comparingInt(Multiset.Entry::getCount)).forEach(
                e -> System.out.println(e + " " + (double)e.getCount()/this.results.size()*100 + "%")
        );
    }


    protected void init(){

        ArrayList<Server> servers = new ArrayList<>();

        for(int i=1;i<=3;i++){
            Server server = new Server();
            server.setIp("192.47.200."+i);
            server.setWeight(i+3);
            server.setCurrWeight(server.getWeight());
            servers.add(server);
        }

        this.servers = servers;


    }




}
