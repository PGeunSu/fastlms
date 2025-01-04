package com.zerobase.fastlms.course.controller;

import com.zerobase.fastlms.util.PagingUtil;

public class BaseController {

  public String getPagerHtml (long totalCount, long pageSize, long pageIndex, String queryString){

    PagingUtil pagingUtil = new PagingUtil(totalCount, pageSize, pageIndex, queryString);

    return pagingUtil.pager();
  }

}
