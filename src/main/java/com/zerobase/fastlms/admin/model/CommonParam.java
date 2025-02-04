package com.zerobase.fastlms.admin.model;

import lombok.Data;

@Data
public class CommonParam {

  long pageIndex;
  long pageSize;

  String searchType;
  String searchValue;

  public long getPageStart(){
    init();
    return (pageIndex - 1) * pageSize;
  }

  public long getPageEnd(){
    init();
    return pageSize;
  }
  public String getQueryString(){
    init();

    StringBuilder sb = new StringBuilder();

    if (searchType != null && searchType.length() > 0){
      sb.append(String.format("searchType=%s", searchType));
    }
    if (searchValue != null && searchValue.length() > 0){
      if (sb.length() > 0) sb.append("&");
      sb.append(String.format("searchValue=%s", searchValue));
    }

    return sb.toString();
  }

  public void init(){
    if (pageIndex < 1) pageIndex = 1;
    if (pageSize <  1) pageSize = 10;
  }


}
