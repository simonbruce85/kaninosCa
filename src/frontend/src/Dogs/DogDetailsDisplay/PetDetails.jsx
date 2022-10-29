import { Badge, Descriptions } from 'antd'
import React from 'react'

const PetDetails = (pet) => {
  return (
    <Descriptions title="Pet Information" bordered className='w-full' column={1}>
    <Descriptions.Item label="Name" labelStyle={{width:"100px"}}>{pet.pet.name}</Descriptions.Item>
    <Descriptions.Item label="Breed" >{pet.pet.breed}</Descriptions.Item>
    <Descriptions.Item label="Type" >{pet.pet.type}</Descriptions.Item>
    <Descriptions.Item label="Weight">{pet.pet.weight}</Descriptions.Item>
    <Descriptions.Item label="DOB" >
    {pet.pet.dob}
    </Descriptions.Item>
    <Descriptions.Item label="Vaccines" >{pet.pet.vaccines}</Descriptions.Item>
    <Descriptions.Item label="Notes" >
    {pet.pet.notes}
    </Descriptions.Item>
    <Descriptions.Item label="Status" >
      <Badge status="processing" text="Running" />
    </Descriptions.Item>
  </Descriptions>
  )
}

export default PetDetails