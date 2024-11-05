# Build Project

![Build Status](https://img.shields.io/github/actions/workflow/status/yuliitezarygml/build/android-ci.yml?branch=main)
![License](https://img.shields.io/github/license/yuliitezarygml/build)
![GitHub Stars](https://img.shields.io/github/stars/yuliitezarygml/build)

## 📋 Описание

**Build Project** — это проект, который автоматизирует процесс сборки Android-приложений. Он настроен на автоматическое создание тегов, сборку APK и AAB файлов, создание релизов, а также отправку уведомлений о состоянии сборки в Telegram.

## 🛠️ Основные функции

- **Автоматическая сборка**: Создает APK и AAB файлы после каждого пуша в основную ветку.
- **Автоматический релиз**: Создает новый релиз на GitHub с последним тегом и загружает APK и AAB файлы.
- **Уведомления в Telegram**: Отправляет уведомления о результате сборки в Telegram с подробной информацией.

## 📂 Структура репозитория

- `app/src` - Исходный код Android-приложения.
- `.github/workflows` - Настройки GitHub Actions для автоматизации CI/CD процесса.
- `README.md` - Документация проекта.

## 🚀 Запуск проекта

1. **Клонирование репозитория**
   ```bash
   git clone https://github.com/yuliitezarygml/build.git
   cd build
