// Smooth scrolling for navigation links
document.querySelectorAll('nav a').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
        e.preventDefault();
        const targetId = this.getAttribute('href');
        if (targetId === '#' || !document.querySelector(targetId)) return;
        document.querySelector(targetId).scrollIntoView({
            behavior: 'smooth'
        });
    });
});

// Enroll modal functionality
function enrollNow() {
    const enrollModal = document.getElementById('enroll-modal');
    if (enrollModal) enrollModal.style.display = 'block';
}

function closeEnrollModal() {
    const enrollModal = document.getElementById('enroll-modal');
    if (enrollModal) enrollModal.style.display = 'none';
}

// Function to set the language
function setLanguage(lang) {
    var elements = document.querySelectorAll('.translate');
    elements.forEach(function(element) {
        var text = element.getAttribute('data-' + lang);
        if (text) {
            element.innerHTML = text;
        }
    });
    localStorage.setItem('language', lang);
}

// Event listener for language selector
document.getElementById('language-selector').addEventListener('change', function() {
    setLanguage(this.value);
});

// Set default language on page load
window.addEventListener('load', function() {
    var savedLanguage = localStorage.getItem('language');
    if (savedLanguage) {
        setLanguage(savedLanguage);
        document.getElementById('language-selector').value = savedLanguage;
    } else {
        // Default to browser language or English
        var browserLang = navigator.language || navigator.userLanguage;
        if (browserLang.startsWith('ru')) {
            setLanguage('ru');
            document.getElementById('language-selector').value = 'ru';
        } else {
            setLanguage('en');
            document.getElementById('language-selector').value = 'en';
        }
    }
});

function toggleDarkMode() {
    document.body.classList.toggle('dark-mode');
    if (document.body.classList.contains('dark-mode')) {
        document.getElementById('theme-toggle').textContent = "Light Mode";
    } else {
        document.getElementById('theme-toggle').textContent = "Dark Mode";
    }
    localStorage.setItem('theme', document.body.classList.contains('dark-mode') ? 'dark' : 'light');
}

document.addEventListener('DOMContentLoaded', function() {
    // Initialize the theme based on localStorage
    var savedTheme = localStorage.getItem('theme');
    if (savedTheme === 'dark') {
        document.body.classList.add('dark-mode');
        document.getElementById('theme-toggle').textContent = "Light Mode";
    } else {
        document.body.classList.remove('dark-mode');
        document.getElementById('theme-toggle').textContent = "Dark Mode";
    }

    // Add event listener to the theme toggle button
    document.getElementById('theme-toggle').addEventListener('click', toggleDarkMode);
});

// FAQ toggle functionality
function toggleFAQ(element) {
    const answer = element.nextElementSibling;
    if (answer.style.display === 'block') {
        answer.style.display = 'none';
    } else {
        answer.style.display = 'block';
        answer.classList.add('slide-down');
    }
}
// Contact form validation
const contactForm = document.getElementById('contact-form');
if (contactForm) {
    contactForm.addEventListener('submit', function(event) {
        event.preventDefault();
        const name = document.getElementById('name').value;
        const email = document.getElementById('email').value;
        if (name === '' || email === '' || !isValidEmail(email)) {
            alert('Please fill in all fields correctly.');
        } else {
            alert('Form submitted successfully!');
            this.reset();
        }
    });
}

function isValidEmail(email) {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(String(email).toLowerCase());
}

// Function to open a modal
function showModal(modalId) {
    document.getElementById(modalId).style.display = "block";
}

// Function to close a modal
function closeModal(modalId) {
    document.getElementById(modalId).style.display = "none";
}

// Function to handle form submission
document.getElementById('enrollment-form').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent default form submission

    // Perform form validation here if needed

    // Close enrollment modal and show confirmation modal
    closeModal('enroll-modal');
    showModal('confirm-modal');
});

document.getElementById('language-selector').addEventListener('change', function() {
    var selectedLang = this.value;
    var elements = document.getElementsByClassName('translate');
    for (var i = 0; i < elements.length; i++) {
        var el = elements[i];
        if (selectedLang === 'en') {
            el.innerHTML = el.getAttribute('data-en');
        } else if (selectedLang === 'ru') {
            el.innerHTML = el.getAttribute('data-ru');
        }
    }
});
document.getElementById('question-form').addEventListener('submit', function(event) {
    event.preventDefault();
    // Add validation if needed
    closeModal('ask-question-modal');
    showModal('question-confirm-modal');
});