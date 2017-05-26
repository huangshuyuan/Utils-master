package com.hsy.utils.utilsdemo.module.home;

import java.util.List;

/**
 * 作者：huangshuyuan on 2017/5/25 16:17
 * 邮箱：hshuyuan@foxmail.com
 */

public class DeilyBean {


    private boolean error;
    private List<Daily> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Daily> getResults() {
        return results;
    }

    public void setResults(List<Daily> results) {
        this.results = results;
    }
}
