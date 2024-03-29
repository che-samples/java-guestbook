/*---------------------------------------------------------------------------------------------
* Copyright (c) 2012-2019 Red Hat, Inc.
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
*
* Contributors:
*   Red Hat, Inc. - initial API and implementation
*--------------------------------------------------------------------------------------------*/
package cloudcode.guestbook.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.http.HttpHeaders;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

/**
 * defines the REST endpoints managed by the server.
 */
@Controller
public class FrontendController {

    private String backendUri = String.format("http://%s/messages",
        "127.0.0.1:8080");

    /**
     * endpoint for the landing page
     * @param model defines model for html template
     * @return the name of the html template to render
     */
    @GetMapping("/")
    public final String main(final Model model) {
        RestTemplate restTemplate = new RestTemplate();
        GuestBookEntry[] response = restTemplate.getForObject(backendUri,
            GuestBookEntry[].class);
        model.addAttribute("messages", response);
        return "home";
    }

    /**
     * endpoint for handling form submission
     * @param formMessage holds date entered in html form
     * @return redirects back to home page
     * @throws URISyntaxException when there is an issue with the backend uri
     */
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public final String post(final GuestBookEntry formMessage)
            throws URISyntaxException {
        URI url = new URI(backendUri);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");
        HttpEntity<GuestBookEntry> httpEntity =
            new HttpEntity<GuestBookEntry>(formMessage, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, httpEntity, String.class);

        return "redirect:/";
    }

}
