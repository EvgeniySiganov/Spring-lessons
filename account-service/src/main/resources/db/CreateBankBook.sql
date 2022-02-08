create schema if not exists dict;
create sequence if not exists dict.currency_id_seq;

create table if not exists dict.currency (
                                          id integer unique not null default nextval('dict.currency_id_seq'),
                                          name varchar,
                                          primary key (id)
);

insert into dict.currency(name)
values ('USD'), ('EUR'), ('RUB'), ('GBR');

create schema if not exists bank;
create sequence if not exists bank.bank_book_id_seq;
create table if not exists bank.bank_book (
    id integer unique not null default nextval('bank.bank_book_id_seq'),
    user_id integer unique not null,
    number varchar not null,
    amount numeric not null,
    currency integer not null,
    primary key (id),
    foreign key (user_id) references ad.users(id),
    foreign key (currency) references dict.currency(id)
);

