INSERT INTO store_sales (store_name, amount)
VALUES
    ('Store A', 10000),
    ('Store B', 15000),
    ('Store C', 20000),
    ('Store D', 12000),
    ('Store E', 18000);

INSERT INTO item (name, type, price, store_id, stock, cpu, capacity)
VALUES
    ('iPhone 12 Pro Max', '스마트폰', 1490000, 1, 100, 'A14 Bionic', '512GB'),
    ('Galaxy S21 Ultra', '스마트폰', 1690000, 2, 80, 'Exynos 2100', '256GB'),
    ('Pixel 6 Pro', '스마트폰', 1290000, 3, 120, 'Google Tensor', '128GB'),
    ('Dell XPS 15', '노트북', 2290000, 4, 50, 'Intel Core i9', '1TB SSD'),
    ('Sony Alpha 7 III', '미러리스 카메라', 2590000, 5, 60, 'BIONZ X', '내부 저장소 없음'),
    ('Xbox Series X', '게임 콘솔', 499000, 1, 30, 'Custom AMD Zen 2', '1TB SSD'),
    ('iPad Air', '태블릿', 849000, 2, 70, 'A14 Bionic', '64GB'),
    ('MacBook Pro', '노트북', 1790000, 3, 40, 'Apple M1 Max', '512GB'),
    ('Sony WH-1000XM4', '헤드폰', 349000, 4, 90, 'Sony HD Nois QN1', '내부 저장소 없음'),
    ('LG OLED CX', 'TV', 2290000, 5, 20, 'LG α9 Gen 3 AI 4K', '128GB');