create schema if not exists dict;
create sequence if not exists dict.currency_id_seq;
create sequence if not exists dict.bank_book_id_seq;

create table if not exists dict.currency (
                                          id integer unique not null default nextval('dict.currency_id_seq'),
                                          name varchar,
                                          primary key (id)
);

insert into dict.currency(name)
values ('USD'), ('EUR'), ('RUB'), ('GBR');

create table if not exists dict.bank_book (
                                             id integer unique not null default nextval('dict.bank_book_id_seq'),
                                             userId integer unique not null,
                                             number varchar not null,
                                             amount numeric not null,
                                             currency integer not null,
                                             primary key (id),
                                             foreign key (userId) references ad.users(id),
                                             foreign key (currency) references dict.currency(id)
);
