package br.com.mariah.controledecontas.genericcrud.filter;

import br.com.mariah.controledecontas.genericcrud.resources.ResourceItem;
import br.com.mariah.controledecontas.genericcrud.resources.ResourceResolver;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class ControllerFilter implements Filter {

    private static final String API_CRUD_BASE_PATH = "/api/crud/";
    private final ResourceResolver resourceResolver;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        if (httpServletRequest.getRequestURI().toLowerCase().contains(API_CRUD_BASE_PATH)) {

            String resourcePath = httpServletRequest.getRequestURI().toLowerCase().replace(API_CRUD_BASE_PATH, "");

            HashMap<Integer, String> possiblePaths = new HashMap<>();

            String[] split = resourcePath.split("/");

            String lastPath = "";

            for (int i = 0; i < split.length; i++) {

                String path = split[i];

                lastPath += "/" + path;

                possiblePaths.put(i, lastPath);
            }

            String pathParam = "";

            for (int i = possiblePaths.size() - 1; i >= 0; i--) {
                String possiblePath = possiblePaths.get(i);

                ResourceItem resolve = resourceResolver.resolve(possiblePath);

                if (Objects.nonNull(resolve)) {

                    httpServletRequest.setAttribute("resourceType", resolve);

                    if (possiblePath.length() != resourcePath.length()) {

                        String replace = ("/" + resourcePath).replace(possiblePath, "");

                        if (replace.startsWith("/")) {

                            replace = replace.replaceFirst("/", "");

                        }

                        pathParam = replace;
                    }

                    break;
                }
            }

            String finalPathParam = pathParam;

            HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(httpServletRequest) {
                @Override
                public String getRequestURI() {

                    if (Objects.nonNull(finalPathParam))

                        return String.format("%s/%s", "/api/crud", finalPathParam);

                    return String.format("%s", "/api/crud");
                }
            };

            filterChain.doFilter(httpServletRequestWrapper, servletResponse);

        } else {

            filterChain.doFilter(httpServletRequest, servletResponse);

        }
    }
}
