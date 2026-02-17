-- Seeding Dashboard Content (Offers)
INSERT INTO dashboard_content (id, type, title, description, icon, bg_color, display_order, is_active, created_at, updated_at) 
VALUES (random_uuid(), 'offer', 'Premium Rewards', 'Get 5% cashback on all utility payments this month.', 'gift-outline', '#047857', 1, true, now(), now());

INSERT INTO dashboard_content (id, type, title, description, icon, bg_color, display_order, is_active, created_at, updated_at) 
VALUES (random_uuid(), 'offer', 'Cyber Protection', 'Your transactions are now protected by bank-grade AI encryption.', 'shield-lock-outline', '#1E3A8A', 2, true, now(), now());

INSERT INTO dashboard_content (id, type, title, description, icon, bg_color, display_order, is_active, created_at, updated_at) 
VALUES (random_uuid(), 'offer', 'Global Transfer', 'Send money to any bank account instantly with zero fees.', 'flash-outline', '#D97706', 3, true, now(), now());

-- Seeding Dashboard Content (Slogans)
INSERT INTO dashboard_content (id, type, text, sub_text, bg_color, accent_color, display_order, is_active, created_at, updated_at) 
VALUES (random_uuid(), 'slogan', 'Precision in every payment, power in every card.', 'Experience the future of digital finance.', '#047857', '#0EA5E9', 1, true, now(), now());

INSERT INTO dashboard_content (id, type, text, sub_text, bg_color, accent_color, display_order, is_active, created_at, updated_at) 
VALUES (random_uuid(), 'slogan', 'Wealth is the ability to fully experience life.', 'Your journey to financial freedom starts here.', '#1E3A8A', '#F59E0B', 2, true, now(), now());
