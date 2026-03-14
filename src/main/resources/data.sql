INSERT INTO courses (id, title, description, category, difficulty, duration_hours, created_at) VALUES
(1, 'New Employee Onboarding Essentials', 'Introduces new team members to workplace expectations, company policies, daily tools, and core internal processes.', 'HR', 'BEGINNER', 6, '2026-01-10 09:00:00'),
(2, 'Workplace Safety Basics', 'Covers practical safety procedures, hazard awareness, and reporting steps to help employees maintain a safe work environment.', 'SAFETY', 'BEGINNER', 4, '2026-01-12 10:30:00'),
(3, 'Internal Communication Tools', 'Explains how employees use approved messaging, email, and collaboration platforms to communicate clearly across teams.', 'HR', 'BEGINNER', 3, '2026-01-15 08:15:00'),
(4, 'Product Knowledge for Sales and Support', 'Provides staff with a clear overview of product features, customer benefits, and common questions raised by clients.', 'PRODUCT_TRAINING', 'INTERMEDIATE', 5, '2026-01-18 11:00:00'),
(5, 'Data Privacy and Compliance', 'Reviews employee responsibilities for handling private data, following company standards, and meeting compliance requirements.', 'COMPLIANCE', 'INTERMEDIATE', 5, '2026-01-20 09:45:00'),
(6, 'Intro to Internal Web Tools', 'Introduces the internal web systems employees use for training, shared resources, and day-to-day workplace tasks.', 'PROGRAMMING', 'BEGINNER', 7, '2026-01-22 13:30:00');

ALTER TABLE courses ALTER COLUMN id RESTART WITH 7;
