package io.jpress.model;

import io.jboot.db.annotation.Table;
import io.jpress.commons.pay.PayStatus;
import io.jpress.model.base.BaseUserOrder;

import java.util.HashMap;
import java.util.Map;

/**
 * Generated by JPress.
 */
@Table(tableName = "user_order", primaryKey = "id")
public class UserOrder extends BaseUserOrder<UserOrder> {

    private static final long serialVersionUID = 1L;

    public UserOrder() {
    }

    /**
     * 交易状态
     */
    public static final int TRADE_STATUS_TRADING = 1; //交易中
    public static final int TRADE_STATUS_COMPLETED = 2; //交易完成（但是可以申请退款）
    public static final int TRADE_STATUS_CANCEL = 3; //取消交易
    public static final int TRADE_STATUS_APPLY_FOR_REFUNDING = 4; //申请退款
    public static final int TRADE_STATUS_REFUSAL_REFUNDING = 5; //拒绝退款
    public static final int TRADE_STATUS_REFUNDING = 6; //退款中
    public static final int TRADE_STATUS_REFUNDED = 7;//退款完成
    public static final int TRADE_STATUS_FINISHED = 9;//交易结束

    public static final Map<Integer, String> tradeStatusTexts = new HashMap<>();

    static {
        tradeStatusTexts.put(TRADE_STATUS_TRADING, "交易中");
        tradeStatusTexts.put(TRADE_STATUS_COMPLETED, "交易完成");
        tradeStatusTexts.put(TRADE_STATUS_CANCEL, "交易结束");
        tradeStatusTexts.put(TRADE_STATUS_APPLY_FOR_REFUNDING, "申请退款中");
        tradeStatusTexts.put(TRADE_STATUS_REFUSAL_REFUNDING, "解决退款");
        tradeStatusTexts.put(TRADE_STATUS_REFUNDING, "退款中");
        tradeStatusTexts.put(TRADE_STATUS_REFUNDED, "退款完成");
        tradeStatusTexts.put(TRADE_STATUS_FINISHED, "交易结束");
    }


    /**
     * 发货状态（物流状态）
     */
    public static final int DELIVERY_STATUS_UNDELIVERY = 1; //未发货
    public static final int DELIVERY_STATUS_DELIVERIED = 2;//已经发货
    public static final int DELIVERY_STATUS_NEED_RE_DELIVERY = 3;//需要补发（特殊情况下，物流出现问题或者其他争议需要重新发货）
    public static final int DELIVERY_STATUS_FINISHED = 8; //用户已收货
    public static final int DELIVERY_STATUS_NONEED = 9;//无需发货

    public static final Map<Integer, String> deliveryStatusTexts = new HashMap<>();

    static {
        deliveryStatusTexts.put(DELIVERY_STATUS_UNDELIVERY, "未发货");
        deliveryStatusTexts.put(DELIVERY_STATUS_DELIVERIED, "已经发货");
        deliveryStatusTexts.put(DELIVERY_STATUS_NEED_RE_DELIVERY, "需要补发");
        deliveryStatusTexts.put(DELIVERY_STATUS_FINISHED, "用户已收货");
        deliveryStatusTexts.put(DELIVERY_STATUS_NONEED, "无需发货");
    }

    /**
     * 配送方式
     */
    public static final int DELIVERY_TYPE_NONEED = 1;   //无需配送
    public static final int DELIVERY_TYPE_EXPRESS = 2;  //快递
    public static final int DELIVERY_TYPE_LOGISTICS = 3; //物流
    public static final int DELIVERY_TYPE_BYCOMPANY = 4; //公司自己配送
    public static final int DELIVERY_TYPE_BYUSER = 5; //用户自提
    public static final Map<Integer, String> deliveryTypeTexts = new HashMap<>();

    static {
        deliveryTypeTexts.put(DELIVERY_TYPE_NONEED, "无需发货");
        deliveryTypeTexts.put(DELIVERY_TYPE_EXPRESS, "快递");
        deliveryTypeTexts.put(DELIVERY_TYPE_LOGISTICS, "物流");
        deliveryTypeTexts.put(DELIVERY_TYPE_BYCOMPANY, "公司配送");
        deliveryTypeTexts.put(DELIVERY_TYPE_BYUSER, "用户自提");
    }


    /**
     * 发布开具状态
     */
    public static final int INVOICE_STATUS_NOT_APPLY = 1; //未申请发票
    public static final int INVOICE_STATUS_APPLYING = 2;//发票申请中
    public static final int INVOICE_STATUS_INVOICING = 3;//发票开具中
    public static final int INVOICE_STATUS_NONEED = 8;//无需开具发票
    public static final int INVOICE_STATUS_INVOICED = 9; //发票已经开具

    public static final Map<Integer, String> invoiceStatusTexts = new HashMap<>();

    static {
        invoiceStatusTexts.put(INVOICE_STATUS_NOT_APPLY, "未申请");
        invoiceStatusTexts.put(INVOICE_STATUS_APPLYING, "申请开具中");
        invoiceStatusTexts.put(INVOICE_STATUS_INVOICING, "发票开具中");
        invoiceStatusTexts.put(INVOICE_STATUS_NONEED, "无需开具发票");
        invoiceStatusTexts.put(INVOICE_STATUS_INVOICED, "发票已开具");
    }

    public boolean isDeliveried(){
        Integer status = getDeliveryStatus();
        return status!= null && (status == DELIVERY_STATUS_DELIVERIED || status == DELIVERY_STATUS_FINISHED);
    }

    public boolean isUnpay(){
        Integer payStatus = getPayStatus();
        return payStatus != null && payStatus == PayStatus.UNPAY.getStatus();
    }

    public boolean isPaySuccess(){
        Integer payStatus = getPayStatus();
        return payStatus != null && payStatus >= PayStatus.SUCCESS_ALIPAY.getStatus();
    }


    public String getTradeStatusStr() {
        return tradeStatusTexts.get(getTradeStatus());
    }

    public String getPayStatusStr() {
        return PayStatus.getTextByInt(getPayStatus());
    }

    public String getDeliveryStatusStr() {
        return deliveryStatusTexts.get(getDeliveryStatus());
    }

    public String getDeliveryTypeStr() {
        return deliveryTypeTexts.get(getDeliveryType());
    }

    public String getInvoiceStatusStr() {
        return invoiceStatusTexts.get(getInvoiceStatus());
    }
}
