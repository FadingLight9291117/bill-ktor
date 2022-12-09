# Ktor 项目

> Kotlin + Gradle + Ktor + Exposed + Docker

## api

### bill

```bash
GET /api/v1/bill/{year}/{month?}/{day?}

POST /api/v1/bill/
Body List<{
    type: Int,
    date: String,
    money: Float,
    cls: String,
    label: String,
    options: String,
}>

DELETE /api/v1/bill/{id}

PUT /api/v1/bill/
Body {
    id: Int?,
    type: Int,
    date: String,
    money: Float,
    cls: String,
    label: String,
    options: String,
}

```

### label

```bash
GET /api/v1/label/

POST /api/v1/label/
Body {
    type: String,
    name: String,
    relativeId: Int?,
}
```