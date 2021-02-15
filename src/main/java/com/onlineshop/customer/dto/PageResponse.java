package com.onlineshop.customer.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PageResponse<T> {
	private int pageIndex;
	private int pageSize;
	private int itemsTotalCount;
	List<T> items;
}
