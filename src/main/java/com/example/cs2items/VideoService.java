package com.example.cs2items;


import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {

        // 数据库连接
        String jdbcUrl = "jdbc:mysql://pxc-hzrddkgw56l30u-pub.polarx.rds.aliyuncs.com:3306/cs2items";
        String dbUser = "stablewlxy";
        String dbPassword = "IVhiK_64EJ3";

    public List<Video> searchVideos(String query) {
        List<Video> results = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 加载 MySQL JDBC 驱动
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
            String sql = "SELECT name, url FROM cs2items WHERE name LIKE ?"; // 假设你的表里有 url 字段
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + query + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String url = resultSet.getString("url"); // 假设你的表有相应的url列
                results.add(new Video(name, url));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }
}