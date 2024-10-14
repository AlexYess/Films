from flask import Flask, request, jsonify
import cohere

app = Flask(__name__)

# Инициализация клиента Cohere с использованием API ключа
cohere_api_key = '13TWQmE5ej0jjVbtPQG23KzJFyIul5YscAkSr5zr'
co = cohere.Client(cohere_api_key)


@app.route('/generate-description', methods=['POST'])
def generate_description():
    data = request.get_json()
    title = data.get('title')

    if not title:
        return jsonify({"error": "Title is required"}), 400

    # # Генерация описания фильма с использованием Cohere
    prompt = (f"Вы писатель, делающий описание для сайта кинотеатра. Ваша задача — создать уникальное "
              f"описание фильма '{title}'. Используйте не более 500 символов.")
    response = co.chat(message=prompt)
    description = response.text.strip()
    # Заглушка

    return jsonify({"description": description})


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
