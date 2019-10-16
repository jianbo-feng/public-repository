package com.feng.activiti.designer.activiti;

import org.activiti.engine.ActivitiException;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;

@RestController
@RequestMapping("/service")
public class StencilseRestResource {

    @RequestMapping(value = "/editor/stencilset", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getStencilset() {
        try {
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream("stencilset.json");
            return IOUtils.toString(stream);
        }
        catch (Exception e) {
            throw new ActivitiException("Error while stencil set", e);
        }
    }
}
