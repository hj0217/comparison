CREATE TABLE TERM_MST (
                          TERM_NO  Number(3)PRIMARY KEY,
                          TERM_TYPE VARCHAR2(99) NOT NULL,
                          TERM_YN VARCHAR2(3) DEFAULT 'Y' NOT NULL,
                          TERM_STARTDATE VARCHAR2(99) NOT NULL,
                          TERM_ENDDATE VARCHAR2(99) NOT NULL,
                          TERM_RGST_BY VARCHAR2(99) NOT NULL,
                          TERM_RGST_DATE DATE NOT NULL,
                          TERM_MDF_BY VARCHAR2(99),
                          TERM_MDF_DATE DATE
);

CREATE TABLE TERM_DTL (
                          TERM_NO NUMBER(3),
                          TERM_LANG VARCHAR(9) NOT NULL,
                          TERM_CNT VARCHAR(9) NOT NULL
);



=============================================================================================================================

CREATE TABLE Product (
                          product_id bigint auto_increment,
                          product_name VARCHAR(9) NOT NULL,
                          product_price VARCHAR(9) NOT NULL
);

CREATE TABLE Product_history (
                                 productHistoryId bigint auto_increment,
                                 productId VARCHAR(9) NOT NULL,
                                 historyType VARCHAR(9) NOT NULL,
                                 historyReason VARCHAR(9) NOT NULL,
                                 historyDetail VARCHAR(9) NOT NULL,
);

DROP TABLE Product_history;