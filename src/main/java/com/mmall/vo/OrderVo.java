package com.mmall.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.xml.internal.ws.spi.db.DatabindingException;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderVo {
    private Long orderNo;
    private Integer shippingId;
    private BigDecimal payment;
    private String paymentDesc;
    private Integer paymentType;
    private Integer postage;
    private Integer status;
    private String statusDes;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date paymentTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date closeTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private List<OrderItemVo> orderItemVoList;

    private String imageHost;

    private String receiverName;

    private ShippingVo shippingVo;

}
