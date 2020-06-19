package com.sts.demodemo.util;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Graph {

    // 邻接矩阵
    private double[][] matrix;
    // 顶点数组
    private String[] vertex;
    // 顶点的数目
    private int vertexNum;
    // 当前结点是否还有下一个结点，判断递归是否结束的标志
    private boolean noNext = false;
    // 所有路径的结果集
    private List<List<String>> result = new ArrayList<List<String>>();

    private List<String> path = new ArrayList<>();

    private boolean[] visited;

    public Graph(double[][] matrix, String[] vertex) {
        if (matrix.length != matrix[0].length) {
            throw new IllegalArgumentException("该邻接矩阵不是方阵");
        }
        if (matrix.length != vertex.length) {
            throw new IllegalArgumentException("结点数量和邻接矩阵大小不一致");
        }
        this.matrix = matrix;
        this.vertex = vertex;
        vertexNum = matrix.length;
        visited = new boolean[vertexNum];
        visited[0] = true;
        path.add(vertex[0]);
    }


    /**
     *
     * @param start
     * @param end
     */
    public void DFS(int start, int end) {
        if(start == end) {
            List<String> list = new ArrayList<>();
            for (String str : path) {
                list.add(str);
            }
            result.add(list);
        }
        for (int i = 0; i < vertexNum; i++){
            //一次遍历中已经经过
            if (visited[i] == false) {
                if(matrix[start][i] > 0) {
                    path.add(vertex[i]);
                    visited[i] = true;
                    DFS(i,end);
                    visited[i] = false;
                    path.remove(vertex[i]);
                }
            }
        }
    }

    /**
     *
     * @param start
     * @param end
     * @return List<List<String>>
     */
    public List<List<String>> returnDFS(int start, int end) {
        DFS(start, end);
        List<List<String>> result1 = new ArrayList<>();
        for (List<String> stringList : result) {
            if (stringList.size() <= 3) {
                result1.add(stringList);
            }
        }
        return result1;
    }

}

