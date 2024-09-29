# springSecurity
Simple Spring Security project with db-based authentication for user and method-level authorization

Authentication Flow Overview:
1. Request Intercepted by Security Filter Chain: The request is captured by the appropriate filter in the Spring Security filter chain, such as UsernamePasswordAuthenticationFilter.

2. Delegate to AuthenticationManager: The filter passes the credentials to the AuthenticationManager for processing.

3. AuthenticationManager Chooses AuthenticationProvider: The AuthenticationManager delegates the authentication request to an appropriate AuthenticationProvider.

4. AuthenticationProvider Validates Credentials: The AuthenticationProvider (e.g., DaoAuthenticationProvider) validates the credentials and returns an Authentication object if successful.

[AuthenticationProvider often works with a UserDetailsService to load user-specific data (such as username, password, roles, and authorities). This service retrieves user information from a data source like a database.]

5. Store in SecurityContext: The resulting Authentication object is stored in SecurityContextHolder, which is used to track the current user's authentication status.

6. Access Decision: Spring Security checks if the user is authorized to access the requested resource.

7. Success or Failure:
  If successful, access is granted.
  If authentication or authorization fails, the user is either redirected to an error page or receives an HTTP error status.
