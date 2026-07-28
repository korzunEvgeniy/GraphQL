INSERT INTO categories (id, name, description) VALUES
                                                   (gen_random_uuid(), 'Электроника', 'Смартфоны, ноутбуки, планшеты и другие гаджеты'),
                                                   (gen_random_uuid(), 'Книги', 'Художественная и учебная литература'),
                                                   (gen_random_uuid(), 'Одежда', 'Мужская, женская и детская одежда'),
                                                   (gen_random_uuid(), 'Продукты', 'Свежие продукты питания'),
                                                   (gen_random_uuid(), 'Спорт', 'Спортивный инвентарь и одежда');

WITH category_ids AS (
    SELECT id, name FROM categories
)
INSERT INTO products (id, name, price, stock, category_id) VALUES
                                                               (gen_random_uuid(), 'iPhone 15 Pro', 999.99, 25, (SELECT id FROM category_ids WHERE name = 'Электроника')),
                                                               (gen_random_uuid(), 'MacBook Pro 16', 2499.99, 10, (SELECT id FROM category_ids WHERE name = 'Электроника')),
                                                               (gen_random_uuid(), 'Samsung Galaxy S24', 899.99, 30, (SELECT id FROM category_ids WHERE name = 'Электроника')),
                                                               (gen_random_uuid(), 'Java: Полное руководство', 89.99, 50, (SELECT id FROM category_ids WHERE name = 'Книги')),
                                                               (gen_random_uuid(), 'Spring Boot в действии', 79.99, 35, (SELECT id FROM category_ids WHERE name = 'Книги')),
                                                               (gen_random_uuid(), 'Футболка хлопковая', 29.99, 100, (SELECT id FROM category_ids WHERE name = 'Одежда')),
                                                               (gen_random_uuid(), 'Джинсы классические', 89.99, 45, (SELECT id FROM category_ids WHERE name = 'Одежда')),
                                                               (gen_random_uuid(), 'Молоко 3.2%', 2.99, 200, (SELECT id FROM category_ids WHERE name = 'Продукты')),
                                                               (gen_random_uuid(), 'Хлеб ржаной', 1.99, 150, (SELECT id FROM category_ids WHERE name = 'Продукты')),
                                                               (gen_random_uuid(), 'Сыр Гауда', 12.99, 80, (SELECT id FROM category_ids WHERE name = 'Продукты')),
                                                               (gen_random_uuid(), 'Баскетбольный мяч', 39.99, 20, (SELECT id FROM category_ids WHERE name = 'Спорт')),
                                                               (gen_random_uuid(), 'Кроссовки беговые', 129.99, 15, (SELECT id FROM category_ids WHERE name = 'Спорт'));