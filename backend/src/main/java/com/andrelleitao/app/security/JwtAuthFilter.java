package com.andrelleitao.app.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Essa classe será usada para filtrar as requisições pegando o token para
 * validar. Ela estende OncePerRequestFilter que faz esse serviço uma vez por
 * requisição.
 * 
 * Vamos primeiro entender como os filtros funcionam. Um Filtro pode ser chamado
 * antes ou depois da execução do servlet. Quando uma solicitação é despachada
 * para um servlet, o RequestDispatcher pode encaminhá-la para outro servlet.
 * Existe a possibilidade de que o outro servlet também tenha o mesmo filtro.
 * Nesses cenários, o mesmo filtro é invocado várias vezes.
 *
 * Mas, talvez queiramos garantir que um filtro específico seja invocado apenas
 * uma vez por solicitação. Um caso de uso comum é ao trabalhar com o Spring
 * Security. Quando uma solicitação passa pela cadeia de filtros, talvez
 * queiramos que algumas das ações de autenticação aconteçam apenas uma vez para
 * a solicitação.
 * 
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailsServiceCustom userDetailsService;
	@Autowired
	private JwtService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException, UsernameNotFoundException {
		
		String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

		// O token está no formado "Bearer token". Para obter o token é
		// necessário o remover da string Bearer <TOKEN>. A validação, referente ao
		// token, só será realizada após a autenticação do usuário pois é após a autenticação que
		// é gerado o token. A rotina abaixo é responsável por validar o token informado na requisição
		// portanto ao realizar a requisição de login o token ainda não existe e portanto a validação só é
		// necessária quando o token for enviado.
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
			// Obtém o token a partir do valor contido no Header Authorization. Ex.: Beaer token.
			// A substring retira o Bearer e deixa apenas o token.
        	token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
		}
        
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        	UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        	
            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);                
            }
		}

		chain.doFilter(request, response);
	}
	
}
