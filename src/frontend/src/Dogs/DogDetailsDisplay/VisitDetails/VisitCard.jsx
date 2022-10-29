import { Badge, Descriptions } from 'antd'
import React from 'react'

const VisitCard = ({visit}) => {
  const {
atHomeTreatment,
clinicTreatment,
date,
diagnostic,
doctorId,
petId,
symptoms,
vaccines,
visitId,
visitReason,
  } = visit

  return (
    <Descriptions bordered className='w-full my-4' column={1}>
    <Descriptions.Item label="Doctor"></Descriptions.Item>
    <Descriptions.Item label="Date" >{date}</Descriptions.Item>
    <Descriptions.Item label="Reason for the Visit" >{visitReason}</Descriptions.Item>
    <Descriptions.Item label="Symptoms">{symptoms}</Descriptions.Item>
    <Descriptions.Item label="Diagnostic" >
    {diagnostic}
    </Descriptions.Item>
    <Descriptions.Item label="at Home Treatment" >{atHomeTreatment}</Descriptions.Item>
    <Descriptions.Item label="On Site treatment" >
    {clinicTreatment}
    </Descriptions.Item>
    <Descriptions.Item label="Vaccines" >
    {vaccines}
    </Descriptions.Item>
    <Descriptions.Item label="Status" >
      <Badge status="processing" text="Running" />
    </Descriptions.Item>
  </Descriptions>
  )
}

export default VisitCard