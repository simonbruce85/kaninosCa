import { Button, Col, Descriptions, Form, Input, Row, Select, Tag } from 'antd'
import React, { useEffect, useState } from 'react'
import { addNewEntry, addNewVaccineToVisit, getEntry, getVaccines } from '../../../client'
import { successNotification } from '../../../Notification'

const VisitCard = ({visit}) => {
  const {
    doctor,
atHomeTreatment,
clinicTreatment,
date,
diagnostic,
petId,
symptoms,
vaccines,
visitId,
visitReason,
pet,
notes,
complExams
  } = visit
  
  const [vaccineList, setVaccineList] = useState([]);
  const [openVaccine, setOpenVaccine] = useState(false);

const fetchVaccines = () =>{
      getVaccines()
      .then(res => res.json())
          .then(data => {
            setVaccineList(data)
            console.log(data)
          })
      }

useEffect(() => {
    fetchVaccines();
}, []);



  const onFinish = ( vaccine ) => {
    console.log(JSON.stringify(vaccine, null, 2));
    console.log(pet.id, vaccine.vaccineId, visitId)
    addNewVaccineToVisit(pet.id, vaccine.vaccineId, visitId)
      .then(() => {
        successNotification(
          "Vaccine successfully added",
          `${vaccine.name} was added to pet ${petId} in visit ${visitId}`
        );
        window.location.reload(false);
      })
      .catch((err) => {
        console.log(err);
        console.log("error addinng new vaccine");
      })
      .finally(() => {
        setOpenVaccine(false);
      });
  };

 

  return (
    <Descriptions bordered className='w-full my-4' column={1}>
    <Descriptions.Item label="Doctor" labelStyle={{width:"100px"}}>{doctor}</Descriptions.Item>
    <Descriptions.Item label="Date" >{date?.slice(8,10)}/{date?.slice(5,7)}/{date?.slice(0,4)} {"(DD/MM/AAAA)"}</Descriptions.Item>
    <Descriptions.Item label="Reason for the Visit" >{visitReason}</Descriptions.Item>
    <Descriptions.Item label="Symptoms">{symptoms}</Descriptions.Item>
    <Descriptions.Item label="Complementary Exams">{complExams}</Descriptions.Item>
    <Descriptions.Item label="Diagnostic" >
    {diagnostic}
    </Descriptions.Item>
    <Descriptions.Item label="at Home Treatment" >{atHomeTreatment}</Descriptions.Item>
    <Descriptions.Item label="On Site treatment" >
    {clinicTreatment}
    </Descriptions.Item>
    <Descriptions.Item label="Notes" >
    {notes}
    </Descriptions.Item>
    <Descriptions.Item label="Vaccines" >
      <div className='flex flex-col lg:max-w-[30%]'>
      <div className='py-1 flex flex-col lg:w-fit '>
    {vaccines?.map((vaccine)=>(
      <Tag color="blue" className='my-1 flex justify-center'>{vaccine.name}</Tag>
    ))}
      </div>
      <div className='"max-w-[10%]"'> 
    <Button
            onClick={() => setOpenVaccine(true)}
            type="default"
            shape="round"
            size="small"
            className={ openVaccine?`hidden`:`flex items-center justify-center" `}
          >
            Add New Vaccine
          </Button>
          </div>
          </div>
          <Form
          // onFinishFailed={onFinishFailed}
          onFinish={onFinish}
          className={openVaccine?`block`:`hidden`}>
            <Form.Item name="visitId" hidden="true">
                <Input  name="visitId" value={visitId} />
            </Form.Item>
          <Row gutter={12}>
          
          <Form.Item
                name="vaccineId"
                className="w-full md:w-[30%] lg:w-[20%]"
              >
                <Select
                  placeholder="Select a vaccine"
                  optionFilterProp="children"
                >
                  {vaccineList.map((vaccine) => (
                    <Select.Option key={vaccine.id} value={vaccine.id}>{vaccine.name}</Select.Option>
                    ))}
                </Select>
              </Form.Item>
          </Row>
          <Form.Item>
                <Button type="default" htmlType="submit">
                  Submit
                </Button>
              </Form.Item>
          </Form>
    </Descriptions.Item>
  </Descriptions>
  )
}

export default VisitCard