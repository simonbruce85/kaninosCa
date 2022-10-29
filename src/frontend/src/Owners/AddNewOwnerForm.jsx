// StudentDrawerForm.js

import {Drawer, Input, Col, Select, Form, Row, Button, Spin} from 'antd';
import {addNewDog, addNewEntry} from "../client";
import {LoadingOutlined} from "@ant-design/icons";
import {useState} from "react";
import {successNotification, errorNotification, infoNotification, warningNotification} from "../Notification";

const {Option} = Select;


const antIcon = <LoadingOutlined style={{ fontSize: 24 }} spin />;


function AddNewOwnerForm({showDrawer, setShowDrawer, fetchOwners}) {
    const onCLose = () => setShowDrawer(false);
    const [submitting, setSubmitting] = useState(false);

    const onFinish = owner => {
        setSubmitting(true);
        console.log(JSON.stringify(owner, null, 2))
        addNewEntry("owners", owner).then(()=> {
            onCLose();
            successNotification("Owner successfully added", `${owner.name} was added to the system`)
            fetchOwners();
        }).catch(err => {
            console.log(err)
        }).finally(()=>{
            setSubmitting(false);
        })
    };

    const onFinishFailed = errorInfo => {
        alert(JSON.stringify(errorInfo, null, 2));
    };

    return <Drawer
        title="Add new owner"
        width={720}
        onClose={onCLose}
        visible={showDrawer}
        bodyStyle={{paddingBottom: 80}}
        footer={
            <div
                style={{
                    textAlign: 'right',
                }}
            >
                <Button onClick={onCLose} style={{marginRight: 8}}>
                    Cancel
                </Button>
            </div>
        }
    >
        <Form layout="vertical"
              onFinishFailed={onFinishFailed}
              onFinish={onFinish}
              hideRequiredMark>
            <Row gutter={16}>
                <Col span={12}>
                    <Form.Item
                        name="name"
                        label="Name"
                        rules={[{required: true, message: 'Please enter student name'}]}
                    >
                        <Input placeholder="Please enter pet name"/>
                    </Form.Item>
                </Col>
                <Col span={12}>
                    <Form.Item
                        name="address"
                        label="Address"
                        rules={[{required: true, message: 'Please enter Address name'}]}
                    >
                        <Input placeholder="Please enter owners address"/>
                    </Form.Item>
                </Col>
            </Row>
            <Row gutter={16}>
                <Col span={12}>
                    <Form.Item
                        name="phone"
                        label="Phone"
                        rules={[{required: true, message: 'Please enter owners phone'}]}
                    >
                        <Input placeholder="Please enter owners phone"/>
                    </Form.Item>
                </Col>
            </Row>
            <Col span={24}>
                    <Form.Item
                        name="notes"
                        label="Notes"
                        rules={[{message: 'Please enter notes'}]}
                    >
                        <Input placeholder="Please enter notes"/>
                    </Form.Item>
                </Col>
            <Row>
                <Col span={12}>
                    <Form.Item >
                        <Button type="default" htmlType="submit">
                            Submit
                        </Button>
                    </Form.Item>
                </Col>
            </Row>
            <Row>
                {submitting && <Spin indicator={antIcon} />}
            </Row>
        </Form>
    </Drawer>
}

export default AddNewOwnerForm;