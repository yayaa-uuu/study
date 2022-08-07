package com.wx.search;

import com.wx.chapter1_3.graph.Graph;
import com.wx.chapter1_3.queue.Queue;

import java.util.Stack;

/**
 * @author wxli
 * @date 2021/9/3 16:34
 */
public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;
    public BreadthFirstPaths(Graph G,int s){
        marked=new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G,s);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new Queue<>();
        marked[s]=true;
        queue.enqueue(s);
        while (!queue.isEmpty()){
            int v=queue.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]){
                    edgeTo[w]=v;
                    marked[w]=true;
                    queue.enqueue(w);
                }
            }
        }
    }
    public boolean hasPathTo(int v){
        return marked[v];
    }
    public Iterable<Integer> pathTo(int v){
        if (!hasPathTo(v)) return null;
        Stack<Integer> path=new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }
}
