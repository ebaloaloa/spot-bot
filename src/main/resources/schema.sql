DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS symbol;

CREATE TABLE IF NOT EXISTS account (
    ID                              VARCHAR(255) NOT NULL,
    USDT_BALANCE                    VARCHAR(255) NOT NULL,
    CONSTRAINT pk_account      PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS symbol (
    SYMBOL               VARCHAR(20)  NOT NULL,
    GALAXY_SCORE         INTEGER      NULL,
    ALT_RANK             INTEGER      NULL,
    STEP_SIZE            DOUBLE       NULL,
    QUOTE_PRECISION      INTEGER      NULL,
    PRICE                DOUBLE       NULL,
    ORDER_ID             BIGINT       NULL,
    IS_TRADING           BOOLEAN      NULL,
    CONSTRAINT pk_symbol           PRIMARY KEY (symbol)
);