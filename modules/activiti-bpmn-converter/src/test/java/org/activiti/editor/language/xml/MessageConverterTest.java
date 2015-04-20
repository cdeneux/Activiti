package org.activiti.editor.language.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.Message;
import org.junit.Test;

public class MessageConverterTest extends AbstractConverterTest {
  
  @Test
  public void connvertXMLToModel() throws Exception {
    BpmnModel bpmnModel = readXMLFile();
    validateModel(bpmnModel);
  }
  
  @Test
  public void convertModelToXML() throws Exception {
    BpmnModel bpmnModel = readXMLFile();
    BpmnModel parsedModel = exportAndReadXMLFile(bpmnModel);
    validateModel(parsedModel);
  }
  
  private void validateModel(BpmnModel model) {
    Message message = model.getMessage("Examples:writeReportId");
    assertNotNull(message);
    assertEquals("Examples:writeReportItem", message.getItemRef());
    assertEquals("writeReportName", message.getName());
    assertEquals("writeReportId", message.getId());

    Message message2 = model.getMessage("Examples:writeReport2Id");
    assertNotNull(message2);
    assertEquals("http://foo.bar.com/Examples:writeReportItem2", message2.getItemRef());
    assertEquals("writeReport2Name", message2.getName());
    assertEquals("writeReport2Id", message2.getId());
    
    Message defaultMessage = model.getMessage("writeReportId");
    assertNotNull(defaultMessage);
    assertEquals("Examples:writeReportItem", defaultMessage.getItemRef());
    assertEquals("writeReportName", defaultMessage.getName());
    assertEquals("writeReportId", defaultMessage.getId());
  }
  
  protected String getResource() {
        return "message.bpmn";
  }
}
