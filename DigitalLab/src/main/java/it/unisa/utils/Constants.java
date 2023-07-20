package it.unisa.utils;

public class Constants 
{
	// Database table names
	public static final String ADMINISTRATOR_TABLE_NAME = "administrator";
	public static final String ADMINISTRATOR_USERNAME = "administrator_username";
	public static final String ADMINISTRATOR_NAME = "administrator_name";
	public static final String ADMINISTRATOR_SURNAME = "administrator_surname";
	public static final String ADMINISTRATOR_PASSWORD = "administrator_password";
	
	public static final String CUSTOMER_TABLE_NAME = "customer";
	public static final String CUSTOMER_USERNAME = "customer_username";
	public static final String CUSTOMER_EMAIL = "customer_email";
	public static final String CUSTOMER_PASSWORD = "customer_password";
	public static final String CUSTOMER_NAME = "customer_name";
	public static final String CUSTOMER_SURNAME = "customer_surname";
	public static final String CUSTOMER_PHONE = "customer_phone";
	
	public static final String SHIPPING_ADDRESS_TABLE_NAME = "shipping_address";
	public static final String SHIPPING_ADDRESS_ID = "shipping_addresS_id";
	public static final String SHIPPING_ADDRESS_PROVINCE = "shipping_address_province";
	public static final String SHIPPING_ADDRESS_STREET = "shipping_address_street";
	public static final String SHIPPING_ADDRESS_CITY = "shipping_address_city";
	public static final String SHIPPING_ADDRESS_ZIP = "shipping_address_zip";
	public static final String SHIPPING_ADDRESS_STREET_NUMBER = "shipping_address_street_number";
	public static final String SHIPPING_ADDRESS_CUSTOMER_USERNAME = "shipping_address_customer_username";
	
	
	public static final String PAYMENT_METHOD_TABLE_NAME = "payment_method";
	public static final String PAYMENT_METHOD_PAN = "payment_method_pan";
	public static final String PAYMENT_METHOD_OWNER = "payment_method_owner";
	public static final String PAYMENT_METHOD_CVV = "payment_method_cvv";
	public static final String PAYMENT_METHOD_EXPIRATION_DATE = "payment_method_expiration_date";
	public static final String PAYMENT_METHOD_CUSTOMER_USERNAME = "payment_method_customer_username";
	
	
	public static final String SHOPPING_CART_TABLE_NAME = "shopping_cart";
	public static final String SHOPPING_CART_CUSTOMER_USERNAME = "shopping_cart_customer_username";
	public static final String SHOPPING_CART_SIZE = "shopping_cart_size";
	
	public static final String ORDER_TABLE_NAME = "customer_order";
	public static final String ORDER_CODE = "customer_order_code";
	public static final String ORDER_STATUS = "customer_order_status";
	public static final String ORDER_TOTAL_AMOUNT = "customer_order_total_amount";
	public static final String ORDER_PAYMENT_DATE = "customer_order_payment_date";
	public static final String ORDER_CUSTOMER_USERNAME = "customer_order_customer_username";
	public static final String ORDER_SHIPPING_ADDRESS_ID = "customer_order_shipping_address_id";
	public static final String ORDER_PAYMENT_METHOD_PAN = "customer_order_payment_method_pan";
	
	public static final String PRODUCT_TABLE_NAME = "product";
	public static final String PRODUCT_CODE = "product_code";
	public static final String PRODUCT_QUANTITY = "product_quantity";
	public static final String PRODUCT_DESCRIPTION = "product_description";
	public static final String PRODUCT_PRICE = "product_price";
	public static final String PRODUCT_BRAND = "product_brand";
	public static final String PRODUCT_MODEL = "product_model";
	public static final String PRODUCT_CATEGORY = "product_category";
	
	
	public static final String PICTURE_TABLE_NAME = "picture";
	public static final String PICTURE_PRODUCT_CODE = "picture_product_code";
	public static final String PICTURE_IMAGE = "picture_image";
	public static final String PICTURE_FILE_NAME = "picture_file_name";
	
	public static final String REVIEW_TABLE_NAME = "review";
	public static final String REVIEW_PRODUCT_CODE = "review_product_code";
	public static final String REVIEW_DESCRIPTION = "review_description";
	public static final String REVIEW_ASSESSMENT = "review_assessment";
	
	public static final String CONTAINS_TABLE_NAME = "shopping_cart_contains";
	public static final String CONTAINS_SHOPPING_CART_CUSTOMER_USERNAME = "contains_shopping_cart_customer_username";
	public static final String CONTAINS_PRODUCT_CODE = "contains_product_code";
	public static final String CONTAINS_PRODUCT_QUANTITY = "contains_product_quantity";
	
	public static final String COMPOSES_TABLE_NAME = "composes";
	public static final String COMPOSES_PRODUCT_CODE = "composes_product_code";
	public static final String COMPOSES_ORDER_CODE = "composes_order_code";
	public static final String COMPOSES_PRODUCT_QUANTITY = "composes_product_quantity";
}
