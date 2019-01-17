package com.umeng.soexample;

import android.webkit.WebView;

import com.umeng.soexample.presenter.ShouPresenter;

/**
 * 用来存放所有接口
 */
public class Contacts {
    //总接口前缀
   //public static final String BASE_URL = "http://mobile.bwstudent.com/small/";
   public static final String BASE_URL = "http://172.17.8.100/small/";

    public static final String USER_UPDATENAME = "user/verify/v1/modifyUserNick";

    public static final String USER_UPDATPWD = "user/verify/v1/modifyUserPwd";
    public static final String SHOU_SHOP = "commodity/v1/commodityList";

    public static final String SHOU_SHOP_SOUSUO = "commodity/v1/findCommodityByKeyword";

    public static final String QUAN_QUERY = "circle/v1/findCircleList";

    public static final String SHOU_XIANGQING = "commodity/v1/findCommodityDetailsById";

    public static final String SHOU_PINLIN = "commodity/v1/CommodityCommentList";

    //查询购物车
    public static final String GWC_QUERY = "order/verify/v1/findShoppingCart";

    //同步购物车
    public static final String GWC_ADD = "order/verify/v1/syncShoppingC" +
            "art";

    //地址列表
    public static final String ADDRESS_LIST = "user/verify/v1/receiveAddressList";

    public static final String ADDRESS_ADD = "user/verify/v1/addReceiveAddress";

    public static final String DINGDAN = "order/verify/v1/createOrder";

   public static final String DINGDAN_QUE = "order/verify/v1/findOrderListByStatus";









}
