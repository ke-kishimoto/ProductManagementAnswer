
-- users
-- password - "password"
-- delete from users;
insert into users(login_id, password, name, role) values('admin', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW','システム管理者', 1);
insert into users(login_id, password, name, role) values('sato', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW','佐藤', 2);
insert into users(login_id, password, name, role) values('suzuki', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW','鈴木', 2);

-- categories
-- delete from categories;
insert into categories(name) values('筆記具');
insert into categories(name) values('オフィス機器');
insert into categories(name) values('事務消耗品');
insert into categories(name) values('紙製品');
insert into categories(name) values('雑貨');

-- products
-- delete from products;
insert into products (product_code, category_id, name, price, description)
values('A0001', 1, 'マッキー(黒)', 160, 'ゼブラ株式会社 線の太さ：太6.0mm、細1.5～2.0mm');
insert into products (product_code, category_id, name, price, description)
values('B0001', 2, '電卓', 935, '');
insert into products (product_code, category_id, name, price, description)
values('C0001', 3, 'ホッチキス芯', 220, '');
insert into products (product_code, category_id, name, price, description)
values('D0001', 4, 'Campus(5冊組)', 220, '');
insert into products (product_code, category_id, name, price, description)
values('E0001', 5, '地球儀', 3800, '');
insert into products (product_code, category_id, name, price, description)
values('D0002', 4, 'ロジカルノート(5冊組)', 386, '');
insert into products (product_code, category_id, name, price, description)
values('B0002', 2, 'レジスター', 24000, '');
insert into products (product_code, category_id, name, price, description)
values('A0002', 1, 'カドケシ', 130, '');
insert into products (product_code, category_id, name, price, description)
values('C0002', 3, 'アラビックヤマト', 200, '');
insert into products (product_code, category_id, name, price, description)
values('E0002', 5, '粘土', 160, '');