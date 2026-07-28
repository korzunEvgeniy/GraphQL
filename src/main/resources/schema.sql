DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS categories CASCADE;

CREATE TABLE IF NOT EXISTS categories (
                                          id UUID PRIMARY KEY,
                                          name VARCHAR(255) NOT NULL UNIQUE,
                                          description TEXT
);

CREATE TABLE IF NOT EXISTS products (
                                        id UUID PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
                                        price DECIMAL(10, 2),
                                        stock INTEGER,
                                        category_id UUID,
                                        CONSTRAINT fk_products_category FOREIGN KEY (category_id)
                                            REFERENCES categories(id) ON DELETE SET NULL
);