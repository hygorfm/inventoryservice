CREATE TABLE tb_order (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    event_id BIGINT,
    customer_id BIGINT,
    quantity BIGINT NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    placed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_order_customer FOREIGN KEY (customer_id) REFERENCES tb_customer(id) ON DELETE SET NULL,
    CONSTRAINT fk_order_event FOREIGN KEY (event_id) REFERENCES tb_event(id) ON DELETE SET NULL
);