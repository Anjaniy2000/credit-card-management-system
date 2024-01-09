package com.anjaniy.creditcardmanagementsystem.models.dto.response;

import com.anjaniy.creditcardmanagementsystem.models.dto.UserDto;
import java.util.List;

public class GetUsersResponse {

    private List<UserDto> users;

    private boolean hasNext;

    private boolean hasPrevious;

    private int nextPage;

    private int totalPages;

    private int totalElements;


    public GetUsersResponse() {
    }

    public GetUsersResponse(
            List<UserDto> user,
            boolean hasNext,
            boolean hasPrevious,
            int nextPage,
            int totalPages,
            int totalElements
    )
    {
        this.users = user;
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
        this.nextPage = nextPage;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public List<UserDto> getUser() {
        return users;
    }

    public void setUser(List<UserDto> user) {
        this.users = user;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    @Override
    public String toString() {
        return "GetUsersResponse{" +
                "user=" + users +
                ", hasNext=" + hasNext +
                ", hasPrevious=" + hasPrevious +
                ", nextPage=" + nextPage +
                ", totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                '}';
    }

}
