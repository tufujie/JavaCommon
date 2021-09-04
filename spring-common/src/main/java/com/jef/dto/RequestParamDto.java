package com.jef.dto;

/**
 * 请求参数
 *
 * @author Jef
 * @date 2020/11/21
 */
public class RequestParamDto {
    /**
     * 开始页
     */
    private int pageNum;
    /**
     * 结束页
     */
    private int pageSize;
    /**
     * 关键词搜索
     */
    private String searchPhrase;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }

    public void setSearchPhrase(String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }
}