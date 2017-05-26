package com.hsy.utils.utilsdemo.module.home;

import java.util.List;

/**
 * 数据实体类
 *
 *
 * 作者：huangshuyuan on 2017/5/25 14:41
 * 邮箱：hshuyuan@foxmail.com
 */
public class CategoryList {
    public static class Category {
        private String name;
        private String imgUrl;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


    }

    public List<Category> getData() {
        return data;
    }

    public void setData(List<Category> data) {
        this.data = data;
    }

    private List<Category> data;
}