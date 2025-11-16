# API Documentation

## Overview

The OTT App uses a template-based API structure that allows for flexible content rendering. The API returns collections of content, each with a specific template type that determines how it's displayed.

## Base URL

Configure in `NetworkModule.kt`:
```kotlin
private const val BASE_URL = "https://your-api-domain.com/"
```

## Endpoints

### 1. Get Home Content

**Endpoint:** `GET /home.json`

**Description:** Fetches the home screen content with multiple collections.

**Response:**

```json
{
  "collections": [
    {
      "id": "string",
      "title": "string",
      "template": "enum",
      "items": [...]
    }
  ]
}
```

## Data Models

### HomeResponse

Root response object for home content.

| Field | Type | Description |
|-------|------|-------------|
| collections | Array<ContentCollection> | List of content collections |

### ContentCollection

A collection of related content items with a display template.

| Field | Type | Required | Description |
|-------|------|----------|-------------|
| id | String | ✅ | Unique collection identifier |
| title | String | ✅ | Display title for the collection |
| template | TemplateType | ✅ | How to render this collection |
| items | Array<ContentItem> | ✅ | Content items in this collection |

### TemplateType (Enum)

Defines how a collection should be rendered.

| Value | Description | UI Layout |
|-------|-------------|-----------|
| `hero_carousel` | Featured content carousel | Full-width auto-scrolling banner |
| `horizontal_list` | Horizontal scrolling list | Scrollable row of cards |
| `vertical_grid` | Grid layout | 3-column grid |
| `featured_banner` | Single featured item | Large banner card |

### ContentItem

Individual piece of content (movie, series, video, etc.).

| Field | Type | Required | Description | Example |
|-------|------|----------|-------------|---------|
| id | String | ✅ | Unique item identifier | "item-123" |
| title | String | ✅ | Content title | "Stranger Things" |
| description | String | ❌ | Content description | "A mysterious disappearance..." |
| imageUrl | String | ✅ | High-res image URL | "https://..." |
| thumbnailUrl | String | ❌ | Thumbnail image URL | "https://..." |
| type | ContentType | ✅ | Type of content | "series" |
| duration | String | ❌ | Duration/length | "4 Seasons" or "2h 30m" |
| rating | Double | ❌ | Content rating | 8.5 |
| year | Int | ❌ | Release year | 2016 |
| genres | Array<String> | ❌ | Genre tags | ["Sci-Fi", "Horror"] |

### ContentType (Enum)

| Value | Description |
|-------|-------------|
| `movie` | Feature film |
| `series` | TV series/show |
| `video` | Short-form video |
| `live` | Live stream |

## Image Specifications

### Image Sizes

| Usage | Recommended Size | Aspect Ratio |
|-------|-----------------|--------------|
| Hero Carousel | 1920x1080 | 16:9 |
| Horizontal List | 600x900 | 2:3 |
| Vertical Grid | 600x900 | 2:3 |
| Featured Banner | 1600x800 | 2:1 |
| Thumbnails | 300x450 | 2:3 |

### Image URLs

- Must be HTTPS
- Should be publicly accessible
- Supported formats: JPG, PNG, WebP
- Consider using CDN for better performance

## Example Responses

### Complete Home Response

```json
{
  "collections": [
    {
      "id": "hero-1",
      "title": "Featured",
      "template": "hero_carousel",
      "items": [
        {
          "id": "item-1",
          "title": "Stranger Things",
          "description": "When a young boy vanishes, a small town uncovers a mystery involving secret experiments.",
          "imageUrl": "https://images.example.com/stranger-things-hero.jpg",
          "thumbnailUrl": "https://images.example.com/stranger-things-thumb.jpg",
          "type": "series",
          "duration": "4 Seasons",
          "rating": 8.7,
          "year": 2016,
          "genres": ["Sci-Fi", "Horror", "Drama"]
        },
        {
          "id": "item-2",
          "title": "The Crown",
          "description": "Follows the political rivalries and romance of Queen Elizabeth II's reign.",
          "imageUrl": "https://images.example.com/crown-hero.jpg",
          "thumbnailUrl": "https://images.example.com/crown-thumb.jpg",
          "type": "series",
          "duration": "6 Seasons",
          "rating": 8.6,
          "year": 2016,
          "genres": ["Drama", "Historical"]
        }
      ]
    },
    {
      "id": "trending-1",
      "title": "Trending Now",
      "template": "horizontal_list",
      "items": [
        {
          "id": "item-3",
          "title": "Breaking Bad",
          "description": "A high school chemistry teacher turned meth producer.",
          "imageUrl": "https://images.example.com/breaking-bad.jpg",
          "thumbnailUrl": "https://images.example.com/breaking-bad-thumb.jpg",
          "type": "series",
          "duration": "5 Seasons",
          "rating": 9.5,
          "year": 2008,
          "genres": ["Crime", "Drama", "Thriller"]
        }
      ]
    },
    {
      "id": "action-1",
      "title": "Action & Adventure",
      "template": "vertical_grid",
      "items": [
        {
          "id": "item-4",
          "title": "John Wick",
          "imageUrl": "https://images.example.com/john-wick.jpg",
          "thumbnailUrl": "https://images.example.com/john-wick-thumb.jpg",
          "type": "movie",
          "duration": "1h 41m",
          "rating": 7.4,
          "year": 2014,
          "genres": ["Action", "Crime", "Thriller"]
        }
      ]
    },
    {
      "id": "featured-1",
      "title": "Editor's Pick",
      "template": "featured_banner",
      "items": [
        {
          "id": "item-5",
          "title": "The Witcher",
          "description": "Geralt of Rivia, a solitary monster hunter, struggles to find his place in a world.",
          "imageUrl": "https://images.example.com/witcher-banner.jpg",
          "thumbnailUrl": "https://images.example.com/witcher-thumb.jpg",
          "type": "series",
          "duration": "3 Seasons",
          "rating": 8.2,
          "year": 2019,
          "genres": ["Action", "Adventure", "Drama"]
        }
      ]
    }
  ]
}
```

### Minimal Valid Response

```json
{
  "collections": [
    {
      "id": "1",
      "title": "Movies",
      "template": "horizontal_list",
      "items": [
        {
          "id": "1",
          "title": "Test Movie",
          "imageUrl": "https://via.placeholder.com/300x450",
          "type": "movie"
        }
      ]
    }
  ]
}
```

## Error Handling

### Network Errors

The app handles network errors gracefully:
- Connection timeout
- No internet connection
- Server errors (5xx)
- Invalid JSON response

### Error States

When an error occurs:
1. Error message is shown to user
2. State updates to show error UI
3. Retry button is provided
4. Previous data (if any) is retained

## Best Practices

### Content Organization

1. **Hero Carousel**
   - Use for 3-5 featured items
   - High-quality landscape images
   - Include compelling descriptions

2. **Horizontal List**
   - Ideal for 5-15 items
   - Portrait orientation thumbnails
   - Group by category or theme

3. **Vertical Grid**
   - Best for large collections (15+ items)
   - Compact display
   - Use for browsing

4. **Featured Banner**
   - Single important item
   - Wide banner image
   - Use sparingly for impact

### Performance Tips

1. **Image Optimization**
   - Use appropriate sizes
   - Consider WebP format
   - Implement lazy loading

2. **Data Structure**
   - Keep descriptions concise
   - Limit collections to 10-15 per page
   - Paginate large datasets

3. **Caching**
   - Implement HTTP caching headers
   - Consider CDN usage
   - Cache images locally

## Testing

### Sample Data

Use the provided `mock-api/home.json` for testing:
- Contains all template types
- Example images from Unsplash
- Realistic content data

### Validation

Ensure your API response:
- ✅ Matches the JSON schema
- ✅ Uses valid enum values
- ✅ Has accessible image URLs
- ✅ Contains at least one collection
- ✅ Each collection has at least one item

## Future API Endpoints (Planned)

- `GET /content/{id}` - Get content details
- `GET /search?q={query}` - Search content
- `GET /library` - Get user's library
- `POST /library/{id}` - Add to library
- `DELETE /library/{id}` - Remove from library
- `GET /profile` - Get user profile

## Support

For API issues or questions, refer to:
- Main README.md
- SETUP_GUIDE.md
- Project issues on GitHub

---

**API Version:** 1.0  
**Last Updated:** 2024-01-16
