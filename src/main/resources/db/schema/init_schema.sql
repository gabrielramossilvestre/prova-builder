CREATE USER postgres WITH password 'builder';

CREATE SCHEMA interview_service;
ALTER USER postgres SET search_path = 'interview_service, public';

GRANT USAGE, CREATE ON SCHEMA interview_service TO postgres;
GRANT ALL ON ALL TABLES IN SCHEMA interview_service TO postgres;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA interview_service TO postgres;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA interview_service TO postgres;
GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA interview_service TO postgres;

SET SCHEMA 'interview_service';
