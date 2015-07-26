package meru.erp.sales.lifecycle;

import app.domain.AppEntityState;
import app.erp.sales.SalesInvoice;
import app.erp.sales.SalesOrder;
import app.erp.sales.SalesOrderLineItem;
import meru.app.AppRequest;
import meru.application.lifecycle.BusinessAppEntityLifeCycle;
import meru.erp.sales.PaymentDocumentId;
import meru.erp.sales.SalesOrderBag;
import meru.erp.sales.SalesOrderBagProvider;
import meru.erp.sales.SalesOrderDocumentId;
import meru.erp.sales.SalesOrderState;
import meru.sys.SystemCalendar;
import meru.sys.uid.key.RandomIntegerKeyGenerator;
import meru.sys.uid.key.RandomKeyGenerator;

public class SalesOrderLifeCycle
        extends BusinessAppEntityLifeCycle<SalesOrder> {

    public static final String SEQ_SALES_ORDER_ID = "SalesOrder.Id";
    public static final String SEQ_SALES_INVOICE_ID = "Invoice.Id";
    public static final String SEQ_PAYMENT_GATEWAY_ID = "PaymentGateway.Id";

    private SalesOrderBagProvider mSalesOrderBagProvider;

    // private ProductLineItemLifeCycle mProductLineItemLifeCycle;

    private RandomKeyGenerator mRandomKeyGenerator;
    private SystemCalendar mSystemCalendar = SystemCalendar.getInstance();

    @Override
    public void init() {

        // mProductLineItemLifeCycle =
        // appEngine.getEntityLifeCycle(ProductLineItem.class,
        // ProductLineItemLifeCycle.class);

        mRandomKeyGenerator = new RandomIntegerKeyGenerator((byte) 4);
    }

    public void setSalesOrderBagProvider(SalesOrderBagProvider salesOrderBagProvider) {

        mSalesOrderBagProvider = salesOrderBagProvider;

    }

    @Override
    public SalesOrder postGet(SalesOrder salesOrder) {

        if (AppRequest.currentRequest()
                      .existsParameter("pdf")) {

        }

        return null;
    }

    @Override
    public boolean preCreate(SalesOrder salesOrder) {

        salesOrder.setSessionId(getSessionId());

        if (salesOrder.getPaymentMethod()
                      .getId() == 1) {
            salesOrder.setState(getSalesOrderState(SalesOrderState.CustomerSubmitted.getCode()));
            setOrderId(salesOrder);
        }
        else {
            salesOrder.setState(getSalesOrderState(SalesOrderState.PendingPayment.getCode()));
            long transSeq = appEngine.nextSequenceNumber(SEQ_PAYMENT_GATEWAY_ID);
            salesOrder.setTransactionId(new PaymentDocumentId(transSeq).toString());
        }
        salesOrder.setPlacedOn(SystemCalendar.getInstance()
                                             .getUTCCalendar());

        salesOrder.setAmount(0F);
        salesOrder.getDelivery()
                  .setDate(mSystemCalendar.getUTCCalendar()
                                          .getTime());
        return true;

    }

    @Override
    public Object postCreate(SalesOrder salesOrder) {

        createOrderItems(salesOrder,
                         mSalesOrderBagProvider.getSalesOrderBag(salesOrder));

        if (SalesOrderState.PendingPayment.getCode() == salesOrder.getState()
                                                                  .getCode()) {

            // return new PGPayment(salesOrder,
            // mMerchantId,
            // mRedirectURL,
            // mWorkingKey);
        }

        return salesOrder;
    }

    public void createOrderItems(SalesOrder salesOrder,
                                 SalesOrderBag<SalesOrderLineItem> salesOrderBag) {

        Long orderId = salesOrder.getId();

        for (SalesOrderLineItem lineItem : salesOrderBag.getLineItems()) {
            lineItem.setSalesOrderId(orderId);
            appEngine.save(lineItem);
        }

        salesOrder.setAmount(salesOrderBag.getGrandTotal());

        if (SalesOrderState.CustomerSubmitted.getCode() == salesOrder.getState()
                                                                     .getCode()) {

            createInvoice(salesOrder);

            salesOrder.getDelivery()
                      .setCode(mRandomKeyGenerator.getKey());

            /*
             * createMail(salesOrder, shoppingBag);
             */
            // updateCustomerLoyalty(salesOrder);

        }

        mSalesOrderBagProvider.clearSalesOrderBag();
    }

    private void setOrderId(SalesOrder salesOrder) {

        long orderSeq = appEngine.nextSequenceNumber(SEQ_SALES_ORDER_ID);
        salesOrder.setOrderId(new SalesOrderDocumentId(orderSeq).toString());

    }

    private AppEntityState getSalesOrderState(int code) {
        return getAppEntityState(SalesOrder.class.getSimpleName(),
                                 code);
    }

    private SalesInvoice createInvoice(SalesOrder salesOrder) {
        SalesInvoice invoice = new SalesInvoice();
        invoice.setSalesOrder(salesOrder);
        invoice.setNumber(SalesOrderDocumentId.getInvoiceNo(salesOrder.getOrderId()));
        appEngine.save(invoice);
        return invoice;
    }

    /*
     * private void popuplateSalesOrderLineItem(SalesOrderLineItem lineItem,
     * SalesOffer salesOffer) {
     * 
     * ProductLineItem productItem = lineItem.getProductLineItem();
     * 
     * if (lineItem.getUnitMrp() == null || lineItem.getUnitMrp() == 0) {
     * lineItem.setUnitMrp(productItem.getMrp());
     * 
     * Tax tax = mProductLineItemLifeCycle.getTax(productItem); if (tax == null)
     * { lineItem.setTaxRate(0F); } else { lineItem.setTaxRate(tax.getRate()); }
     * }
     * 
     * Float discount = null; PropertyGroup discountType = null;
     * 
     * if (lineItem.getDiscount() == null) {
     * 
     * if (salesOffer != null && salesOffer.getDiscount() >
     * lineItem.getDiscount()) { discount = salesOffer.getDiscount();
     * discountType = salesOffer.getDiscountType(); } else { discount =
     * productItem.getDiscount(); discountType = productItem.getDiscountType();
     * }
     * 
     * lineItem.setDiscount(discount); lineItem.setDiscountType(discountType); }
     * else {
     * 
     * discount = lineItem.getDiscount(); discountType =
     * lineItem.getDiscountType(); }
     * 
     * float unitPrice = SalesOrderBag.getUnitPrice(lineItem.getUnitMrp(),
     * discount, discountType);
     * 
     * lineItem.setUnitPrice(unitPrice);
     * 
     * float totalPrice = SalesOrderBag.getTotalPrice(lineItem.getUnitMrp(),
     * (lineItem.getNetQuantity() == null) ? lineItem.getQuantity() :
     * lineItem.getNetQuantity(), lineItem.getDiscount(),
     * lineItem.getDiscountType());
     * 
     * lineItem.setTotalPrice(totalPrice);
     * 
     * if (lineItem.getTaxRate() == null || lineItem.getTaxRate() == -1) {
     * 
     * Tax tax = mProductLineItemLifeCycle.getTax(productItem); if (tax == null)
     * { lineItem.setTaxRate(0F); } else { lineItem.setTaxRate(tax.getRate()); }
     * 
     * } }
     */
}
