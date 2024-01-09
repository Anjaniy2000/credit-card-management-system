package com.anjaniy.creditcardmanagementsystem.models.dto.request;

public class GetUsersRequest {

    private int startPage;

    private int pageSize;

    public GetUsersRequest() {
    }

    public GetUsersRequest(
            int startPage,
            int pageSize
    )
    {
        this.startPage = startPage;
        this.pageSize = pageSize;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "GetUsersRequest{" +
                "startPage=" + startPage +
                ", pageSize=" + pageSize +
                '}';
    }

}
