package com.rjp.eaction.network;

public class UrlConst {
    public static final String RELEASE_HOST = "http://118.89.217.77:8090/edu/";

    public static final String URL_HOME_CATEGORY = "classify/findHome.jhtml";
    public static final String URL_HOME_BOOK_LIST = "details/findPage.jhtml";

    public static final String URL_BOOK_LIST = "book/findAll.jhtml";
    public static final String URL_BOOK_DETAIL = "details/findbookId.jhtml";

    public static final String URL_STORE_CATEGORY = "classify/findGoods.jhtml";

    /**
     * 注册
     */
    public static final String URL_REGISTER = "user/register.jhtml";
    public static final String URL_REGISTER_GET_CODE = "user/getcode.jhtml";

    /**
     * 退出
     */
    public static final String URL_LOGOUT = "user/exit.jhtml";

    /**
     * 分页查询   bookClassifyId  Page  Rows
     */
    public static final String URL_STORE_CATEGORY_GOODS = "details/findPage.jhtml";

    /**
     * 商品详情    Id
     */
    public static final String URL_STORE_GOODS_DETAIL = "details/findId.jhtml";


    public static final String URL_ORDER_ALL = "eduorder/findAll.jhtml";
    public static final String URL_ORDER_NOT_PAY = "eduorder/notpaying.jhtml";
    public static final String URL_ORDER_NOT_RECEIVE = "eduorder/notreceiving.jhtml";
    public static final String URL_ORDER_COMPLETE = "eduorder/complete.jhtml";
    public static final String URL_ORDER_CANCEL = "eduorder/cancel.jhtml";

    /**
     * 订单商品信息  Id
     */
    public static final String URL_ORDER_GOOD_DETAIL = "details/findId";
    /**
     * 订单详情   orderId
     */
    public static final String URL_ORDER_DETAIL = "eduorder/findId";


    /**
     * 生成订单
     * address	string	物流地址
     * shopWay	string	支付方式
     * bookDetails	string	商品id
     * num	int	购买数量
     * Credit_amount	decimal	减免金额
     * deductions	string	减免方式
     * payment	decimal	实付金额
     * shippingName	string	配送方式
     * invoiceType	int	发票类型
     */
    public static final String URL_ADD_ORDER = "eduorder/add.jhtml";

    /**
     * 获取用户信息
     */
    public static final String URL_USER_ACCOUNT = "user/findOne.jhtml";

    /**
     * 新增地址
     * name	string	收货人
     * phone	string	收货人手机号
     * area	string	所在区域
     * address	string	详细地址
     * isDefault	int	是否是默认地址(0是 1不是)
     */
    public static final String URL_USER_ADD_ADDRESS = "useraddress/add.jhtml";

    /**
     * 获取用户地址
     */
    public static final String URL_USER_ALL_ADDRESS = "useraddress/findAll.jhtml";

    /**
     * 修改用户密码
     * oldpassword	string	老密码
     * newpassword	string	新密码
     */
    public static final String URL_USER_MODIFY_PASSWORD = "user/updatepwd.jhtml";

    /**
     * 已读书籍
     */
    public static final String URL_MINE_HAD_READ = "readbook/findusercount.jhtml";
    /**
     * 今日陪伴
     */
    public static final String URL_MINE_TODAY_WITH = "readtime/add.jhtml";
    /**
     * 阅读时长
     */
    public static final String URL_MINE_READ_TIME = "family/findPage.jhtml";
    /**
     * 亲子陪伴
     */
    public static final String URL_MINE_FAMILY_WITH = "family/add.jhtml";
}
