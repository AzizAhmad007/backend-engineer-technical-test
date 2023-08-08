--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-08-08 22:22:59

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 3362 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 51187)
-- Name: mst_add_facility; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mst_add_facility (
    facility_id bigint NOT NULL,
    additional_facilities character varying(255),
    price numeric(19,2)
);


ALTER TABLE public.mst_add_facility OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 51216)
-- Name: mst_add_facilty_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.mst_add_facilty_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.mst_add_facilty_seq OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 51149)
-- Name: mst_customer_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.mst_customer_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.mst_customer_seq OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 51192)
-- Name: mst_employee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mst_employee (
    employee_id bigint NOT NULL,
    employee_email character varying(255),
    employee_phone character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    password character varying(255),
    token character varying(255)
);


ALTER TABLE public.mst_employee OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 51217)
-- Name: mst_employee_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.mst_employee_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.mst_employee_seq OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 51199)
-- Name: mst_room; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mst_room (
    room_id bigint NOT NULL,
    price numeric(19,2),
    room_name character varying(255)
);


ALTER TABLE public.mst_room OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 51218)
-- Name: mst_room_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.mst_room_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.mst_room_seq OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 58943)
-- Name: mst_transaksi; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mst_transaksi (
    transaksi_id bigint NOT NULL,
    date_checkin timestamp without time zone,
    date_checkout timestamp without time zone,
    employee_id bigint,
    facility_id bigint,
    length_of_stay numeric(19,2),
    number_of_room numeric(19,2),
    room_id bigint,
    status character varying(255),
    total numeric(19,2),
    user_id bigint
);


ALTER TABLE public.mst_transaksi OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 51219)
-- Name: mst_transaksi_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.mst_transaksi_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.mst_transaksi_seq OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 51209)
-- Name: mst_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mst_user (
    user_id bigint NOT NULL,
    first_name character varying(255),
    last_name character varying(255),
    password character varying(255),
    token character varying(255),
    user_email character varying(255),
    user_phone character varying(255)
);


ALTER TABLE public.mst_user OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 51220)
-- Name: mst_user_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.mst_user_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.mst_user_seq OWNER TO postgres;

--
-- TOC entry 3347 (class 0 OID 51187)
-- Dependencies: 215
-- Data for Name: mst_add_facility; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.mst_add_facility (facility_id, additional_facilities, price) FROM stdin;
1	breakfast	1000.00
2	extra bad	1000.00
\.


--
-- TOC entry 3348 (class 0 OID 51192)
-- Dependencies: 216
-- Data for Name: mst_employee; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.mst_employee (employee_id, employee_email, employee_phone, first_name, last_name, password, token) FROM stdin;
1	Tsar@gmail.com	0812-0000-9999	Tsar	Rusia	827ccb0eea8a706c4c34a16891f84e7b	\N
\.


--
-- TOC entry 3349 (class 0 OID 51199)
-- Dependencies: 217
-- Data for Name: mst_room; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.mst_room (room_id, price, room_name) FROM stdin;
1	5000.00	single
2	5000.00	twin
3	5000.00	deluxe
4	5000.00	family
\.


--
-- TOC entry 3356 (class 0 OID 58943)
-- Dependencies: 224
-- Data for Name: mst_transaksi; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.mst_transaksi (transaksi_id, date_checkin, date_checkout, employee_id, facility_id, length_of_stay, number_of_room, room_id, status, total, user_id) FROM stdin;
1	2023-08-08 07:00:00	2023-08-10 07:00:00	1	1	2.00	1.00	1	check out	12000.00	1
\.


--
-- TOC entry 3350 (class 0 OID 51209)
-- Dependencies: 218
-- Data for Name: mst_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.mst_user (user_id, first_name, last_name, password, token, user_email, user_phone) FROM stdin;
1	Benjamin	Franklin	827ccb0eea8a706c4c34a16891f84e7b	\N	benjamin@gmail.com	0812-0000-9999
\.


--
-- TOC entry 3363 (class 0 OID 0)
-- Dependencies: 219
-- Name: mst_add_facilty_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.mst_add_facilty_seq', 51, true);


--
-- TOC entry 3364 (class 0 OID 0)
-- Dependencies: 214
-- Name: mst_customer_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.mst_customer_seq', 1, false);


--
-- TOC entry 3365 (class 0 OID 0)
-- Dependencies: 220
-- Name: mst_employee_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.mst_employee_seq', 51, true);


--
-- TOC entry 3366 (class 0 OID 0)
-- Dependencies: 221
-- Name: mst_room_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.mst_room_seq', 51, true);


--
-- TOC entry 3367 (class 0 OID 0)
-- Dependencies: 222
-- Name: mst_transaksi_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.mst_transaksi_seq', 1, true);


--
-- TOC entry 3368 (class 0 OID 0)
-- Dependencies: 223
-- Name: mst_user_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.mst_user_seq', 51, true);


--
-- TOC entry 3195 (class 2606 OID 51191)
-- Name: mst_add_facility mst_add_facility_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mst_add_facility
    ADD CONSTRAINT mst_add_facility_pkey PRIMARY KEY (facility_id);


--
-- TOC entry 3197 (class 2606 OID 51198)
-- Name: mst_employee mst_employee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mst_employee
    ADD CONSTRAINT mst_employee_pkey PRIMARY KEY (employee_id);


--
-- TOC entry 3199 (class 2606 OID 51203)
-- Name: mst_room mst_room_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mst_room
    ADD CONSTRAINT mst_room_pkey PRIMARY KEY (room_id);


--
-- TOC entry 3203 (class 2606 OID 58947)
-- Name: mst_transaksi mst_transaksi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mst_transaksi
    ADD CONSTRAINT mst_transaksi_pkey PRIMARY KEY (transaksi_id);


--
-- TOC entry 3201 (class 2606 OID 51215)
-- Name: mst_user mst_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mst_user
    ADD CONSTRAINT mst_user_pkey PRIMARY KEY (user_id);


-- Completed on 2023-08-08 22:22:59

--
-- PostgreSQL database dump complete
--

