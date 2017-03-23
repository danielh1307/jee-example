alter table Person drop constraint FK7hs06ply1oy9a1735sii1iced
alter table Vehicle drop constraint FKird0jsckjobdsnjeof5686hog
drop table if exists Address cascade
drop table if exists Person cascade
drop table if exists Vehicle cascade
drop sequence if exists hibernate_sequence
