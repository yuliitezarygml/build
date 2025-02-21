{% extends 'base.html' %}

{% block title %}LC - Home{% endblock %}

{% block content %}
<style>
.header {
    text-align: center;
    padding: 10px 10px 40px 10px;
    background-color: #1e1e1e;
}

.header .title {
    font-size: 2.8rem;
    font-weight: 700;
    color: #1f4fff;
    margin-bottom: 5px;
}

.header .subtitle {
    font-size: 1.2rem;
    font-weight: 400;
    color: #ccc;
    margin-bottom: 35px;
}

.header .btn-download {
    padding: 15px 30px;
    font-size: 1.2rem;
    font-weight: 600;
    color: #fff;
    background-color: #1f4fff;
    border: 2px solid #1f4fff;
    border-radius: 3px;
    text-transform: uppercase;
    text-decoration: none;
    cursor: pointer;
    position: relative;
    overflow: hidden; /* To hide the pseudo-element overflow */
    transition: all 0.4s ease; /* Smooth transition for all properties */
    box-shadow: 0 0 15px rgba(255, 95, 31, 0.4); /* Add glow effect */
}

.header .btn-download:hover {
    border-color: #1f4fff;
    box-shadow: 0 0 25px rgba(255, 95, 31, 0.8); /* Increase glow effect on hover */
}

.header .btn-download:hover::before {
    width: 0;
    height: 0;
}

.header .btn-download span {
    position: relative;
    z-index: 1; /* Ensures text is always on top */
}

.header .btn-download:active {
    transform: scale(0.95); /* Slight shrink effect on button click */
    box-shadow: 0 0 20px rgba(255, 95, 31, 0.6); /* Subtle glow on click */
}


.cta-section {
    text-align: center;
    padding: 10px 20px;
    background-color: #1e1e1e;
    margin-top: 10px;
}

.cta-section h2 {
    font-size: 1.6rem;
    color: #1f4fff;
    margin-bottom: 20px;
}

.gallery {
    display: flex;
    justify-content: center;
    gap: 35px;
    margin-bottom: 20px;
    flex-wrap: wrap;
}

.gallery-item {
    border: 1px solid;
    border-color: #1f4fff;
    flex: 0 0 25%;
    overflow: hidden;
    border-radius: 8px;
    background-color: #1e1e1e;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
    transition: transform 0.3s ease, box-shadow 0.3s ease;
    cursor: pointer;
}

.gallery-item:hover {
    transform: scale(1.05);
    box-shadow: 0 0 15px rgba(255, 95, 31, 0.4);
}

.gallery-image {
    width: 100%;
    height: auto;
    display: block;
    transition: transform 0.3s ease;
}

.gallery-item:hover .gallery-image {
    transform: scale(1.05);
}

@media (max-width: 768px) {
    .gallery {
        flex-direction: column;
        align-items: center;
    }

    .gallery-item {
        max-width: 80%;
        margin-bottom: 20px;
    }

    .gallery-item:nth-child(2),
    .gallery-item:nth-child(3) {
        display: none;
    }
}

.social-media ul {
    list-style-type: none;
    padding: 0;
    margin-top: 25px;
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
}

.social-media ul li {
    margin: 0 15px;
}

.social-media ul li a {
    color: #fff;
    font-size: 28px;
    transition: color 0.3s ease, transform 0.3s ease;
}

.social-media ul li a:hover {
    color: #1f4fff;
    transform: translateY(-15px);
}


/* Анимация fade-in */
@keyframes fadeIn {
    from {
        opacity: 0;
        transform: translateY(20px);
    }
    to {
        opacity: 1;
        transform: translateY(0);
    }
}

.fade-in {
    animation: fadeIn 0.6s ease-out forwards; /* Ускорена до 0.6s */
    opacity: 0;
}

/* Bounce-in анимация для галереи */
@keyframes bounceIn {
    0% {
        opacity: 0;
        transform: scale(0.8);
    }
    50% {
        opacity: 0.8;
        transform: scale(1.1);
    }
    100% {
        opacity: 1;
        transform: scale(1);
    }
}

.bounce-in {
    animation: bounceIn 0.5s ease-out forwards; /* Ускорена до 0.8s */
    opacity: 0;
}

/* Плавающий эффект для иконок соцсетей */
@keyframes float {
    0%, 100% {
        transform: translateY(0);
    }
    50% {
        transform: translateY(-3px); /* Немного уменьшено */
    }
}

.float-icon {
    animation: float 1.5s ease-in-out infinite;
}

/* Add styles for footer and buttons */
.footer {
    background-color: #1e1e1e;
    text-align: center;
    padding: 10px 0;
}

.footer a {
    color: #fff;
    text-decoration: none;
    margin: 0 15px;
    font-weight: 600;
    font-size: 1.2rem;
    transition: color 0.3s ease;
}

.footer a:hover {
    color: #1f4fff;
}

/* Keyframes for stat cards on-load animation */
@keyframes fadeInUp {
    from {
        opacity: 0;
        transform: translateY(50px);
    }
    to {
        opacity: 1;
        transform: translateY(0);
    }
}

/* Keyframes for footer and <hr> animations */
@keyframes slideIn {
    from {
        opacity: 0;
        transform: translateX(-100%);
    }
    to {
        opacity: 1;
        transform: translateX(0);
    }
}

/* Apply animations to stats */
.stat-card {
    opacity: 0;
    animation: fadeInUp 0.8s ease forwards;
}

/* Stagger the animations for each stat card */
.stat:nth-child(1) .stat-card {
    animation-delay: 0.2s;
}

.stat:nth-child(2) .stat-card {
    animation-delay: 0.4s;
}

.stat:nth-child(3) .stat-card {
    animation-delay: 0.6s;
}

/* Add animation for footer links */
.footer a {
    opacity: 0;
    animation: slideIn 1s ease forwards;
}

.footer a:nth-child(1) {
    animation-delay: 0.25s;
}

.footer a:nth-child(2) {
    animation-delay: 0.5s;
}

.footer a:nth-child(3) {
    animation-delay: 0.75s;
}

html {
    scroll-behavior: smooth;
}

.video-container {
    position: relative;
    width: 100%;
    max-width: 800px;
    margin: 20px auto;
    padding: 0 15px;
}

.video-wrapper {
    position: relative;
    padding-bottom: 56.25%; /* Соотношение сторон 16:9 */
    height: 0;
    overflow: hidden;
}

.video-wrapper iframe {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    border: none;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

@media (max-width: 768px) {
    .video-container {
        padding: 0 10px;
    }
}

.faq-link {
    display: inline-block;
    padding: 8px 16px;
    background-color: #1f4fff;
    color: #ffffff !important;
    text-decoration: none;
    border-radius: 4px;
    font-weight: 400;
    transition: all 0.3s ease;
    margin-top: 10px;
    border: none;
    text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.8);
    letter-spacing: 0.5px;
    font-size: 1.1rem;
    filter: brightness(1.2);
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
}

.faq-link:hover {
    background-color: #1a43e6;
    transform: translateY(-1px);
    box-shadow: 0 2px 8px rgba(31, 79, 255, 0.4);
    color: #ffffff !important;
    filter: brightness(1.3);
    text-shadow: 1px 1px 4px rgba(0, 0, 0, 0.9);
}

.faq-link:visited,
.faq-link:active,
.faq-link:focus {
    color: #ffffff !important;
}

/* Стили для сайдбара */
.sidebar {
    background-color: #1e1e1e;
    height: 100vh;
    position: fixed;
    left: 0;
    top: 0;
    width: 250px;
    z-index: 1000;
    display: flex;
    flex-direction: column;
    border-right: 4px solid transparent;
    border-image: linear-gradient(to bottom, #1f4fff, #ff4800, #ffa500) 1;
    box-shadow: 0 0 10px rgba(31, 79, 255, 0.4);
}

/* Верхняя линия */
.sidebar::before {
    content: '';
    height: 4px;
    background: linear-gradient(to right, #1f4fff, #ff4800, #ffa500);
    width: 100%;
    box-shadow: 0 0 10px rgba(31, 79, 255, 0.4);
}



/* Логотип и заголовок */
.logo-container {
    display: flex;
    align-items: center;
    padding: 15px;
    border-bottom: 2px solid #1f4fff;
    box-shadow: 0 2px 10px rgba(31, 79, 255, 0.3);
}

.logo-container img {
    width: 40px;
    height: 40px;
    margin-right: 10px;
}

.logo-text {
    color: #fff;
    font-size: 1.2rem;
}

.logo-subtext {
    color: #ccc;
    font-size: 0.8rem;
}

/* Навигационные ссылки */
.sidebar a {
    color: #fff;
    text-decoration: none;
    padding: 12px 15px;
    display: flex;
    align-items: center;
    transition: all 0.3s ease;
}

.sidebar a:hover {
    background: linear-gradient(to right, transparent, rgba(255, 72, 0, 0.2));
}

/* Нижняя секция */
.sidebar-footer {
    margin-top: auto;
    padding: 15px;
    border-top: 2px solid #1f4fff;
    box-shadow: 0 -2px 10px rgba(31, 79, 255, 0.3);
}

/* Анимация для активных элементов */
.sidebar a.active {
    background: linear-gradient(to right, transparent, rgba(255, 72, 0, 0.3));
    border-right: 2px solid #ff4800;
}

/* Иконки */
.sidebar i {
    margin-right: 10px;
    width: 20px;
    text-align: center;
}

/* Basic Section Styling */
.section {
    background-color: #1e1e1e;
    border-radius: 10px;
    opacity: 0;
    transform: translateY(50px);
    animation: fadeInUp 0.4s forwards;
}

.section-title {
    text-align: center;
    margin: 20px 0;
    font-size: 2rem;
    color: #1f4fff;
}

.faq-item {
    margin-bottom: 10px;
    padding: 15px;
    background-color: #1a1a1a;
    border-radius: 8px;
    transition: background-color 0.15s ease;
    opacity: 0;
    transform: translateX(-50px);
    animation: fadeInLeft 0.4s forwards;
}

.faq-item:nth-child(2) {
    animation-delay: 0.1s;
}

.faq-item:nth-child(3) {
    animation-delay: 0.2s;
}

.faq-item:nth-child(4) {
    animation-delay: 0.3s;
}

.faq-item:nth-child(5) {
    animation-delay: 0.4s;
}

.faq-item:hover {
    background-color: #333;
}

.faq-question {   
    font-size: 1.2rem;
    font-weight: bold;
    color: #1f4fff;
    margin-bottom: 10px;
}

.faq-answer {
    font-size: 1rem;
    color: #ccc;
}

.faq-link {
    color: #1f4fff;
    text-decoration: none;
}

.faq-link:hover {
    color: #f48c42;
}

.problem-section {
    margin-top: 5px;
    border-radius: 8px;
    border: 1px solid #1f4fff;
    opacity: 0;
    animation-delay: 0.4s;
    transition: background-color 0.3s ease;
}

.problem-section h3 {
    text-align: left;
    font-size: 1.5rem;
    color: #1f4fff;
    margin-bottom: 15px;
}

.problem-section ul {
    margin-top: 10px;
    list-style-type: disc;
    margin-left: 20px;
    color: #ccc;
}

.problem-section ul li {
    margin-bottom: 10px;
}

.external-link {
    color: #1f4fff;
    text-decoration: none;
}

.external-link:hover {
    color: #f48c42;
}

/* TOS Section Styling */
#selection ul {
    list-style-type: disc;
    margin-left: 20px;
}

#selection ul li {
    margin-top: 10px;
    font-size: 1rem;
    margin-bottom: 10px;
    color: #ccc;
}


@keyframes gradientAnimation {
    0% {
        background-position: 0 0;
    }
    50% {
        background-position: 100% 0;
    }
    100% {
        background-position: 0 0;
    }
}

.tos {
    text-align: center;
    margin-bottom: 5px;
}

.tos h {
    justify-content: center;
    display: flex;
    flex-wrap: wrap;
    margin-top: 10px;
    font-size: 1rem;
    color: #ccc;
}

/* Animations */
@keyframes fadeInUp {
    to {
        opacity: 1;
        transform: translateY(0);
    }
}

@keyframes fadeInLeft {
    to {
        opacity: 1;
        transform: translateX(0);
    }
}
    .stats-container {
        display: flex;
        justify-content: space-around;
        gap: 20px;
        margin: 20px 0;
        padding: 0 10px;
    }

    .stat {
        flex: 1;
        display: flex;
        justify-content: center;
    }

    .stat-card {
        color: #fff;
        padding: 20px;
        text-align: center;
        transition: transform 0.3s, box-shadow 0.3s;
        width: 100%;
        max-width: 300px;
    }

    .stat-icon {
        font-size: 3rem;
        color: #1f4fff;
        margin-bottom: 10px;
    }

    .stat-value {
        font-size: 2rem;
        font-weight: bold;
        margin: 10px 0;
    }

    .stat-label {
        font-size: 1.2rem;
        color: #ccc;
    }
    @keyframes fadeIn {
            0% {
                opacity: 0;
                transform: translateY(30px);
            }
            100% {
                opacity: 1;
                transform: translateY(0);
            }
        }
    .inf {
        padding: 10px;
    }
</style>
<div>
    <header class="header fade-in">
        <div class="container">
            <h1 class="title">RCV V2</h1>
            <p class="subtitle"> is a gaming project that allows users to add games to Steam. With it, users can quickly and easily expand their library by adding new games without any hassle.</p>
            <a href="https://swa-recloud.fun//api/download/SWASetup.exe" class="btn-download fade-in">Download</a>
        </div>
    </header>

    <section class="gallery">
        <div class="gallery-item bounce-in" style="animation-delay: 0.2s;">
            <img src="/static/1.png" alt="Feature 1" class="gallery-image">
        </div>
        <div class="gallery-item bounce-in" style="animation-delay: 0.4s;">
            <img src="/static/2.png" alt="Feature 2" class="gallery-image">
        </div>
        <div class="gallery-item bounce-in" style="animation-delay: 0.6s;">
            <img src="/static/3.png" alt="Feature 3" class="gallery-image">
        </div>
    </section>
    <footer class="footer">
        <a href="#faq" style="">FAQ</a><a onmouseover="this.style.color='#fff'">|</a><a href="#tos">TOS</a>
    </footer>
    <section class="cta-section fade-in" style="animation-delay: 0.4s;">
        <h2>Stay Connected!</h2>
        <div class="social-media">
            <ul>
                <li><a href="https://t.me/swa_recloud" target="_blank"><i class="fab fa-telegram"></i></a></li>
                <li><a href="https://discord.gg/hwVjmSF9dm" target="_blank"><i class="fab fa-discord"></i></a></li>
            </ul>
        </div>
    </section>
</div>

<hr>
<div class="video-container">
    <div class="video-wrapper">
        <iframe 
            src="https://www.youtube.com/embed/pI6_6lDtwmU?si=WLuqjU1uEQ1buGUS" 
            title="YouTube video player" 
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" 
            referrerpolicy="strict-origin-when-cross-origin" 
            allowfullscreen>
        </iframe>
    </div>
</div>
<hr>
<div class="stats-container">
    <div class="stat">
        <div class="stat-card">
            <i class="fa fa-globe stat-icon"></i>
            <div class="stat-value" id="online-users-value">0</div>
            <div class="stat-label">Online Users</div>
        </div>
    </div>

    <div class="stat">
        <div class="stat-card">
            <i class="fas fa-user-clock stat-icon"></i>
            <div class="stat-value" id="daily-users-value">0</div>
            <div class="stat-label">Daily Users</div>
        </div>
    </div>

    <div class="stat">
        <div class="stat-card">
            <i class="fas fa-users stat-icon"></i>
            <div class="stat-value" id="total-users-value">0</div>
            <div class="stat-label">Total Users</div>
        </div>
    </div>
</div>

<hr>

<!-- FAQ Section -->
<section id="faq" class="section">
    <div class="container">
        <h2 class="section-title">FAQ | Frequently Asked Questions</h2>

        <div class="faq-item">
            <h3 class="faq-question">RCV V2 is safe?</h3>
            <p class="faq-answer">RCV V2 is a closed-source application, but we believe in being open and transparent with our users. Our code is not obfuscated or encrypted, meaning you can still check it for yourself using third-party tools if you're interested. While the source code isn't available for direct download, we're committed to providing a clear and honest experience. If you ever want to explore how things work behind the scenes, feel free to use other apps to take a closer look.</p>
        </div>

        <div class="faq-item">
            <h3 class="faq-question">Game size is 0kb</h3>
            <p class="faq-answer">
                If your game is 0kb size, you should check if your game updates are not turned off in steamtools.
                <br><a href="#0kb" class="faq-link" onclick="toggleProblemSection(event)">How to fix | Step By Step Guide</a>
                <div class="problem-section" style="overflow: hidden; transition: max-height 0.3s ease-out, opacity 0.3s ease-out; max-height: 0; opacity: 0;">
                    <div class="inf">
                        <p>To fix this problem u need turn off "<strong>Disable Game Updates</strong>" in steamtools!</p>
                        <ul>
                            <li>Open RCV V2</li>
                            <li>Go settings</li>
                            <li>Click steamtools</li>
                            <li>Right click on steamtools</li>
                            <li>Hover on "Unlock Steam Solution"</li>
                            <li>Turn off "Disable Game Updates"</li>
                            <li>Restart steam</li>
                        </ul>
                        <h>If your "Disable Game Updates" was disabled, just enable it and disable again, should fix your problem!</h>
                    </div>
                </div>
            </p>
        </div>

        <div class="faq-item">
            <h3 class="faq-question">Game does not appear after adding</h3>
            <p class="faq-answer">If your game is not appear after success adding of it, u have to reinstall steamtools!
                <br><a href="#rest" class="faq-link" onclick="toggleProblemSection(event)">How to fix | Step By Step Guide</a></p>
                <div class="problem-section" style="overflow: hidden; transition: max-height 0.3s ease-out, opacity 0.3s ease-out; max-height: 0; opacity: 0;">
                    <div class="inf">
                        <p>To fix this problem u need reinstall <strong>SteamTools</strong>!</p>
                        <ul>
                            <li>Open your steam folder</li>
                            <li>Delete folder /config</li>
                            <li>Open RCV V2</li>
                            <li>Go settings</li>
                            <li>Click steamtools</li>
                            <li>Add games again</li>
                            <li>Restart steam</li>
                        </ul>
                        <h>This should fix your problem with steamtools, make sure your RCV V2 is showing "Unlocked" on bottom of right corner, if its "Locked", unlock it in settings!</h>
                    </div>
                </div>
        </div>

        <div class="faq-item">
            <h3 class="faq-question">Content Is Still Encrypted</h3>
            <p class="faq-answer">If when u download game steam say "Content Is Still Encrypted", disable game DLC's and download game!
                <br><a href="#cise" class="faq-link" onclick="toggleProblemSection(event)">How to fix | Step By Step Guide</a></p>
                <div class="problem-section" style="overflow: hidden; transition: max-height 0.3s ease-out, opacity 0.3s ease-out; max-height: 0; opacity: 0;">
                    <div class="inf">
                        <p>To fix this problem u need turn disable DLC's in game!</p>
                        <ul>
                            <li>Open RCV V2</li>
                            <li>Go DLC's</li>
                            <li>Enter your game ID</li>
                            <li>Click disable</li>
                            <li>Restart steam</li>
                        </ul>
                        <h>This should fix your problem with "Content Is Still Encrypted"!</h>
                    </div>
                </div>
        </div>

        <div class="faq-item">
            <h3 class="faq-question">Problem is not listed?</h3>
            <p class="faq-answer">Join our discord server and open ticket, our support will help you!
                <br><a target="_blank" href="https://discord.gg/Mt5hmVce2d" class="faq-link">Discord Server</a></p>
        </div>
    </div>
</section>
<hr>   

<!-- TOS Section -->
<section id="tos" class="section">
    <div class="container">
        <div class="tos social-media">
            <h2 class="section-title">TOS | Terms Of Service</h2>
            <p>By using RCV V2, you agree to the following terms:</p>
            <h>Content provided is for personal use only.</h>
            <h>You may not redistribute any files downloaded from this site.</h>
            <h>We are not responsible for any damages caused by the use of our platform.</h>
            <ul>
                <li><a href="https://t.me/swa_recloud" target="_blank"><i class="fab fa-telegram"></i></a></li>
                <li><a href="https://discord.gg/hwVjmSF9dm" target="_blank"><i class="fab fa-discord"></i></a></li>
            </ul>
    </div>
</section>
<script>
function toggleProblemSection(event) {
    event.preventDefault(); // Prevent the default anchor behavior
    const problemSection = event.target.closest('.faq-item').querySelector('.problem-section');
    if (problemSection) {
        if (problemSection.style.maxHeight === '0px' || problemSection.style.maxHeight === '') {
            problemSection.style.maxHeight = problemSection.scrollHeight + 'px';
            problemSection.style.opacity = 1;
        } else {
            problemSection.style.maxHeight = '0px';
            problemSection.style.opacity = 0;
        }
    }
}
</script>
<script>
const endpoint = 'https://swa-recloud.fun//api/stats';
const statsMapping = {
    'online-users': 'online_users',
    'daily-users': 'daily_users',
    'total-users': 'total_users'
};

async function fetchData() {
    try {
        const response = await fetch(endpoint);
        if (!response.ok) {
            throw new Error('Error fetching stats');
        }

        const data = await response.json();
        console.log("API Response:", data);

        for (const [elementId, apiKey] of Object.entries(statsMapping)) {
            const element = document.getElementById(`${elementId}-value`);
            const value = data[apiKey] !== undefined ? data[apiKey] : 0; 
            console.log(`${elementId} = ${value}`); 
            element.textContent = value;

            setTimeout(() => {
                element.style.animation = '';
            }, 500);
        }
    } catch (error) {
        console.error('Error fetching or processing data:', error); 
        for (const elementId of Object.keys(statsMapping)) {
            const element = document.getElementById(`${elementId}-value`);
            element.textContent = `0`; 
        }
    }
}

fetchData();

// Добавить регулярное обновление статистики
setInterval(fetchData, 10000); // Обновление каждые 10 секунд
</script>
<script>
// Объект с переводами для главной страницы
const pageTranslations = {
    'en': {
        'title': 'RCV V2',
        'subtitle': 'is a gaming project that allows users to add games to Steam. With it, users can quickly and easily expand their library by adding new games without any hassle.',
        'download': 'Download',
        'stay_connected': 'Stay Connected!',
        'faq_title': 'FAQ | Frequently Asked Questions',
        'tos_title': 'TOS | Terms Of Service',
        // FAQ переводы
        'is_safe_question': 'RCV V2 is safe?',
        'is_safe_answer': 'RCV V2 is a closed-source application, but we believe in being open and transparent with our users. Our code is not obfuscated or encrypted, meaning you can still check it for yourself using third-party tools if you\'re interested. While the source code isn\'t available for direct download, we\'re committed to providing a clear and honest experience. If you ever want to explore how things work behind the scenes, feel free to use other apps to take a closer look.',
        'game_size_question': 'Game size is 0kb',
        'game_size_answer': 'If your game is 0kb size, you should check if your game updates are not turned off in steamtools.',
        'game_not_appear_question': 'Game does not appear after adding',
        'game_not_appear_answer': 'If your game is not appear after success adding of it, u have to reinstall steamtools!',
        'content_encrypted_question': 'Content Is Still Encrypted',
        'content_encrypted_answer': 'If when u download game steam say "Content Is Still Encrypted", disable game DLC\'s and download game!',
        'problem_not_listed_question': 'Problem is not listed?',
        'problem_not_listed_answer': 'Join our discord server and open ticket, our support will help you!',
        'how_to_fix': 'How to fix | Step By Step Guide',
        'discord_server': 'Discord Server',
        'steamtools_fix_title': 'To fix this problem u need reinstall SteamTools!',
        'steamtools_steps': [
            'Open your steam folder',
            'Delete folder /config',
            'Open RCV V2',
            'Go settings',
            'Click steamtools',
            'Add games again',
            'Restart steam'
        ],
        'steamtools_note': 'This should fix your problem with steamtools, make sure your RCV V2 is showing "Unlocked" on bottom of right corner, if its "Locked", unlock it in settings!',
        
        'encrypted_fix_title': 'To fix this problem u need turn disable DLC\'s in game!',
        'encrypted_steps': [
            'Open RCV V2',
            'Go DLC\'s',
            'Enter your game ID',
            'Click disable',
            'Restart steam'
        ],
        'encrypted_note': 'This should fix your problem with "Content Is Still Encrypted"!'
    },
    'ru': {
        'title': 'RCV V2',
        'subtitle': 'это игровой проект, который позволяет пользователям добавлять игры в Steam. С его помощью пользователи могут быстро и легко расширить свою библиотеку, добавляя новые игры без лишних хлопот.',
        'download': 'Скачать',
        'stay_connected': 'Оставайтесь на связи!',
        'faq_title': 'FAQ | Часто задаваемые вопросы',
        'tos_title': 'ПС | Пользовательское соглашение',
        // FAQ переводы
        'is_safe_question': 'RCV V2 безопасен?',
        'is_safe_answer': 'RCV V2 является закрытым приложением, но мы стремимся к открытости и прозрачности с нашими пользователями. Наш код не обфусцирован и не зашифрован, что означает, что вы можете проверить его самостоятельно с помощью сторонних инструментов. Хотя исходный код недоступен для прямого скачивания, мы стремимся обеспечить честный и прозрачный опыт использования. Если вы хотите изучить, как всё работает за кулисами, не стесняйтесь использовать другие приложения для более детального анализа.',
        'game_size_question': 'Размер игры 0kb',
        'game_size_answer': 'Если размер вашей игры 0kb, проверьте, не отключены ли обновления игры в steamtools.',
        'game_not_appear_question': 'Игра не появляется после добавления',
        'game_not_appear_answer': 'Если игра не появляется после успешного добавления, вам необходимо переустановить steamtools!',
        'content_encrypted_question': 'Контент всё ещё зашифрован',
        'content_encrypted_answer': 'Если при загрузке игры Steam пишет "Content Is Still Encrypted", отключите DLC игры и загрузите игру!',
        'problem_not_listed_question': 'Проблема не указана в списке?',
        'problem_not_listed_answer': 'Присоединяйтесь к нашему Discord серверу и создайте тикет, наша поддержка поможет вам!',
        'how_to_fix': 'Как исправить | Пошаговое руководство',
        'discord_server': 'Discord Сервер',
        'steamtools_fix_title': 'Для решения этой проблемы вам нужно переустановить SteamTools!',
        'steamtools_steps': [
            'Откройте папку Steam',
            'Удалите папку /config',
            'Откройте RCV V2',
            'Перейдите в настройки',
            'Нажмите steamtools',
            'Добавьте игры снова',
            'Перезапустите Steam'
        ],
        'steamtools_note': 'Это должно исправить проблему с steamtools. Убедитесь, что RCV V2 показывает "Разблокировано" в правом нижнем углу. Если показывает "Заблокировано", разблокируйте его в настройках!',
        
        'encrypted_fix_title': 'Для решения этой проблемы вам нужно отключить DLC в игре!',
        'encrypted_steps': [
            'Откройте RCV V2',
            'Перейдите в DLC',
            'Введите ID вашей игры',
            'Нажмите отключить',
            'Перезапустите Steam'
        ],
        'encrypted_note': 'Это должно исправить проблему "Content Is Still Encrypted"!'
    }
};

// Обновляем функцию обработки события
document.addEventListener('languageChanged', (event) => {
    const lang = event.detail.language;
    
    // Обновляем основной контент
    document.querySelector('.title').textContent = pageTranslations[lang]['title'];
    document.querySelector('.subtitle').textContent = pageTranslations[lang]['subtitle'];
    document.querySelector('.btn-download').textContent = pageTranslations[lang]['download'];
    document.querySelector('.cta-section h2').textContent = pageTranslations[lang]['stay_connected'];
    document.querySelector('#faq .section-title').textContent = pageTranslations[lang]['faq_title'];
    document.querySelector('#tos .section-title').textContent = pageTranslations[lang]['tos_title'];
    
    // Обновляем все вопросы и ответы в FAQ
    document.querySelectorAll('.faq-item').forEach(item => {
        const questionEl = item.querySelector('.faq-question');
        const answerEl = item.querySelector('.faq-answer');
        const originalText = questionEl.textContent.trim();

        // Обновляем первый вопрос о безопасности
        if (originalText.includes('RCV V2 is safe') || originalText.includes('RCV V2 безопасен')) {
            questionEl.textContent = pageTranslations[lang]['is_safe_question'];
            answerEl.textContent = pageTranslations[lang]['is_safe_answer'];
        }
        // Обновляем вопрос о размере игры
        else if (originalText.includes('Game size is 0kb') || originalText.includes('Размер игры 0kb')) {
            questionEl.textContent = pageTranslations[lang]['game_size_question'];
            const linkHtml = answerEl.querySelector('a') ? answerEl.querySelector('a').outerHTML : '';
            answerEl.innerHTML = pageTranslations[lang]['game_size_answer'] + '<br>' + linkHtml;
        }
        // Обновляем вопрос о том, что игра не появляется после добавления
        else if (originalText.includes('Game does not appear') || originalText.includes('Игра не появляется')) {
            questionEl.textContent = pageTranslations[lang]['game_not_appear_question'];
            const linkHtml = answerEl.querySelector('a') ? answerEl.querySelector('a').outerHTML : '';
            answerEl.innerHTML = pageTranslations[lang]['game_not_appear_answer'] + '<br>' + linkHtml;
        }
        // Обновляем вопрос о том, что контент всё ещё зашифрован
        else if (originalText.includes('Content Is Still Encrypted') || originalText.includes('Контент всё ещё зашифрован')) {
            questionEl.textContent = pageTranslations[lang]['content_encrypted_question'];
            const linkHtml = answerEl.querySelector('a') ? answerEl.querySelector('a').outerHTML : '';
            answerEl.innerHTML = pageTranslations[lang]['content_encrypted_answer'] + '<br>' + linkHtml;
        }
        // Обновляем вопрос о том, что проблема не указана в списке
        else if (originalText.includes('Problem is not listed') || originalText.includes('Проблема не указана')) {
            questionEl.textContent = pageTranslations[lang]['problem_not_listed_question'];
            const linkHtml = answerEl.querySelector('a') ? answerEl.querySelector('a').outerHTML : '';
            answerEl.innerHTML = pageTranslations[lang]['problem_not_listed_answer'] + '<br>' + linkHtml;
        }
    });
    
    // Обновляем все кнопки "How to fix"
    document.querySelectorAll('.faq-link').forEach(link => {
        if (link.textContent.includes('How to fix') || link.textContent.includes('Как исправить')) {
            link.textContent = pageTranslations[lang]['how_to_fix'];
        }
    });
    
    document.querySelectorAll('.discord-server').forEach(link => {
        if (link.textContent.includes('Discord Server')) {
            link.textContent = pageTranslations[lang]['discord_server'];
        }
    });

    // Обновляем инструкции для Steamtools
    document.querySelectorAll('.problem-section').forEach(section => {
        const content = section.querySelector('.inf');
        if (content) {
            if (content.textContent.includes('steamtools')) {
                // Обновляем заголовок
                content.querySelector('p').innerHTML = `<strong>${pageTranslations[lang]['steamtools_fix_title']}</strong>`;
                
                // Обновляем шаги
                const steps = content.querySelector('ul');
                steps.innerHTML = pageTranslations[lang]['steamtools_steps']
                    .map(step => `<li>${step}</li>`)
                    .join('');
                
                // Обновляем примечание
                content.querySelector('h').textContent = pageTranslations[lang]['steamtools_note'];
            } else if (content.textContent.includes('Content Is Still Encrypted')) {
                // Обновляем заголовок
                content.querySelector('p').innerHTML = `<strong>${pageTranslations[lang]['encrypted_fix_title']}</strong>`;
                
                // Обновляем шаги
                const steps = content.querySelector('ul');
                steps.innerHTML = pageTranslations[lang]['encrypted_steps']
                    .map(step => `<li>${step}</li>`)
                    .join('');
                
                // Обновляем примечание
                content.querySelector('h').textContent = pageTranslations[lang]['encrypted_note'];
            }
        }
    });
});

// Добавляем проверку при загрузке страницы
document.addEventListener('DOMContentLoaded', () => {
    // Проверяем сохраненный язык
    const savedLanguage = localStorage.getItem('selectedLanguage') || 'en';
    // Создаем и отправляем событие изменения языка
    const event = new CustomEvent('languageChanged', { detail: { language: savedLanguage } });
    document.dispatchEvent(event);
});

// Добавляем обработчик для кнопок переключения языка
document.querySelectorAll('.language-btn').forEach(btn => {
    btn.addEventListener('click', function() {
        const lang = this.textContent.toLowerCase();
        localStorage.setItem('selectedLanguage', lang);
        const event = new CustomEvent('languageChanged', { detail: { language: lang } });
        document.dispatchEvent(event);
    });
});
</script>
{% endblock %}
