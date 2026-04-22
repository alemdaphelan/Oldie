## API Specifications

**Global Note:** When done authenticating, all requests have to go through the JWT filter to get the personal information.

### 1. Landing Page & Authentication

| Feature | Route | Endpoint | Method | Note |
| :--- | :--- | :--- | :--- | :--- |
| Landing page (root) | `/` | `/api/v1/auth/verify` | GET | Only view for unauthenticated client |
| Sign up | `/register` | `/api/v1/auth/register` | POST | |
| Sign in | `/login` | `/api/v1/auth/login` | POST | |
| OAuth's callback | `/auth/callback` | `/api/v1/auth/oauth/{provider_name}/callback` | GET | Redirecting requires that client has to authenticate successfully and the JWT must be stored in HTTPOnly or localStorage |

### 2. Client Management

| Feature | Route | Endpoint | Method | Note |
| :--- | :--- | :--- | :--- | :--- |
| Information's management | `/account/settings` | `/api/v1/users/me` | GET | For account owner |
| Edit information | `account/settings/info` | `/api/v1/users/` | POST | |
| Profile's management | `/profile/:username` | `/api/v1/users/profile/:user_id` | GET | |
| Follow | N/A | `/api/v1/users/:user_id/follow` | POST | |
| Unfollow | N/A | `/api/v1/users/:user_id/follow` | GET | |

### 3. Listing 
*(Only when client request redirection from OAuth's callback)*

| Feature | Route | Endpoint | Method |
| :--- | :--- | :--- | :--- |
| View Listing | `/Homepage` | `/api/v1/listings?category=&search=&filter=` | GET |
| View Listing's detail | `listings/:listing_id` | `/api/v1/listings/:listing_id` | GET |
| Create listing | `listings/create` | `/api/v1/listing/create` | POST |
| Update listing | `listings/update` | `/api/v1/listing/:listing_id/update` | POST |
| Remove Listing | `listings/create` | `/api/v1/listing/:listing_id/remove` | PUT |

> **Notes for View Listing:**
> * We can handle for various amount of category or filter by duplicating it in endpoint's query section. Ex: `/api/v1/listings?category=&category=&...&search=&<filter_name>=&<filter_name>=&<filter_name>=`
> * Viewing have to go through: `filter` -> `recommendation` -> `popularity` (Skip recommendation layer if the client is new). 
> * Uses **FP-Growth algorithm**.

### 4. Chat

| Feature | Route | Endpoint | Method |
| :--- | :--- | :--- | :--- |
| List conversations | `/conversations/` | `/api/v1/conversations` | GET |
| View Message | `/conversations/:conversation_id/` | `/api/v1/conversations/:conversation_id/messages` | GET |
| Sending message | `/conversations/:conversation_id` | `/api/v1/conversations/:conversation_id/messages` | POST |

### 5. Order

| Feature | Route | Endpoint | Method |
| :--- | :--- | :--- | :--- |
| Viewing history | `/orders?from=&to=&status=` | `/api/v1/orders` | GET |
| Order Detail | `/orders/:order_id` | `/api/v1/orders/:order_id` | GET |
| Review user | `/orders/:order_id/reviews` | `/api/v1/orders/:order_id/reviews` | POST |
| Confirm order | `/checkout/:listing_id` | `/api/v1/listings/:listing_id` | GET |
| Making order | N/A | `/api/v1/orders/` | POST |