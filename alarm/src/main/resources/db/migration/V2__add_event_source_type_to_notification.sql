ALTER TABLE notification
    ADD COLUMN event_source_type VARCHAR(255) NOT NULL AFTER event_type;