// StudentDrawerForm.js
import {isMobile} from 'react-device-detect';
import {
  Drawer,
  Input,
  Col,
  Select,
  Form,
  Row,
  Button,
  Spin,
  DatePicker,
} from "antd";
import { addNewEntry } from "../client";
import { LoadingOutlined } from "@ant-design/icons";
import { useEffect, useState } from "react";
import {
  successNotification,
  errorNotification,
} from "../Notification";
import {getOwnersIdAndName} from "../client"

const { Option } = Select;

const antIcon = <LoadingOutlined style={{ fontSize: 24 }} spin />;

function AddNewDogForm({ showDrawer, setShowDrawer, fetchPets }) {
  const onCLose = () => setShowDrawer(false);
  const [submitting, setSubmitting] = useState(false);
  const [ownerList, setOwnerList] = useState([])
  const [form] = Form.useForm();//used to reset the form values after submitting

  //fetching all owners Id and Name to show on dropdown menu
const fetchOwnerIdAndPets = () =>{
    getOwnersIdAndName()
    .then(res => res.json())
        .then(data => {
            setOwnerList(data)
        })
    }

  useEffect(() => {
    fetchOwnerIdAndPets();
}, []);
//fetching all owners Id and Name to show on dropdown menu
  
  const onFinish = (pet) => {
    setSubmitting(true);
    console.log("no error agregando");
    console.log(JSON.stringify(pet, null, 2));
    addNewEntry("pets", pet)
      .then(() => {
        onCLose();
        successNotification(
          "Pet successfully added",
          `${pet.name} was added to the system`
        );
        fetchPets();
      }).catch((err) => {
        console.log("error agregando");
        console.log(err.response);
        err.response.json().then((res) => {
          errorNotification(
            "There was and issue",
            `${res.message} [statusCode:${res.status}] [${res.error}]`,
            "bottomLeft"
          );
        });
      })
      .finally(() => {
        setSubmitting(false);
        // window.location.reload(false); used to refresh the page
        form.resetFields();//used to reset the form values after submitting
      });
  };

  const onFinishFailed = (errorInfo) => {
    // alert(JSON.stringify(errorInfo, null, 2));
    errorNotification(
      "There was and issue",
      'Please fill all the required fields',
      "bottomLeft"
    );
  };

  const onChangeDate = (date, dateString) => {
    console.log(date, dateString);
  };

  // const onChangeType = (value) => {
  //   console.log(value);
  // };

  // const onChangeOwnerDropdown = (value) => {
  //   console.log(`selected ${value}`);
  // };
  // const onSearchOwnerDropDown = (e) => {
  //   //check if something has been typed on the owner field to avoid undefined.
  //   if (e)
  //   setSearch(e.target.value);
  // };

  return (
    <Drawer
      title="Add new pet"
      onClose={onCLose}
      width={isMobile?300:720}
      visible={showDrawer}
      bodyStyle={{ 
        paddingBottom: 80
      }}
      footer={
        <div
          style={{
            textAlign: "right",
          }}
        >
          <Button onClick={onCLose} style={{ marginRight: 8 }}>
            Cancel
          </Button>
        </div>
      }
    >
      <Form
        layout="vertical"
        onFinishFailed={onFinishFailed}
        onFinish={onFinish}
        hideRequiredMark
        form={form}//used to reset the form values after submitting
      >
        {isMobile?<>
            <Form.Item name="owner_id" label="Owner" rules={[{ required: true }]}>
              <Select
                showSearch
                placeholder="Select a person"
                optionFilterProp="children"
                // onChange={onChangeOwnerDropdown}
                // onSearch={onSearchOwnerDropDown}
              >
                {ownerList?.map((owner)=>(
                    <Option key={owner[0]} value={owner[0]}>{owner[1]}</Option>
                ))}
              </Select>
            </Form.Item>
            <Form.Item
              name="name"
              label="Name"
              rules={[{ required: true, message: "Please enter pet name" }]}
            >
              <Input placeholder="Please enter pet name" />
            </Form.Item>
            <Form.Item
              name="type"
              label="Type"
              rules={[{ required: true, message: "Please enter pet type" }]}
            >
              <Select
                placeholder="Select a person"
              >
                <Option value="dog">Dog</Option>
                <Option value="cat">Cat</Option>
                <Option value="other">Other</Option>
              </Select>
              </Form.Item>
            <Form.Item
              name="breed"
              label="Breed"
              rules={[{ required: true, message: "Please enter pet breed" }]}
            >
              <Input placeholder="Please enter pet breed" />
            </Form.Item>
            <Form.Item
              name="color"
              label="Color"
              rules={[{ required: true, message: "Please enter pet color" }]}
            >
              <Input placeholder="Please enter pet color" />
            </Form.Item>
            <Form.Item
              name="gender"
              label="Gender"
              rules={[{ required: true, message: "Please enter pet gender" }]}
            >
              <Select placeholder="Please select a gender">
                <Option value="MALE">MALE</Option>
                <Option value="FEMALE">FEMALE</Option>
              </Select>
            </Form.Item>
            <Form.Item
              name="weight"
              label="Weight"
              rules={[{ required: true, message: "Please enter pet weight" }]}
            >
              <Input placeholder="Please enter pet weight" />
            </Form.Item>
            <Form.Item
              name="dob"
              label="Date of Birth"
              rules={[{ required: true, message: "Please enter pet weight" }]}
            >
              <DatePicker onChange={onChangeDate} />
            </Form.Item>
            <Form.Item
              name="vaccines"
              label="Vaccines"
              rules={[{ required: true, message: "Please enter pet vaccines" }]}
            >
              <Input placeholder="Please enter pet vaccines" />
            </Form.Item>
            <Form.Item
              name="notes"
              label="Notes"
              rules={[{ message: "Please enter pet weight" }]}
            >
              <Input.TextArea placeholder="Please enter pet weight" />
            </Form.Item>
        <Row>
          <Col span={12}>
            <Form.Item>
              <Button type="default" htmlType="submit">
                Submit
              </Button>
            </Form.Item>
          </Col>
        </Row>
        <Row>{submitting && <Spin indicator={antIcon} />}</Row></>:<> <Row gutter={16}>
          <Col span={12}>
            <Form.Item name="ownerIndicator" label="Owner" rules={[{ required: true }]}>
              <Select
                showSearch
                placeholder="Select a person"
                optionFilterProp="children"
                // onChange={onChangeOwnerDropdown}
                // onSearch={onSearchOwnerDropDown}
              >
                {ownerList?.map((owner)=>(
                    <Option key={owner[0]} value={owner[0]}>{owner[1]}</Option>
                ))}
              </Select>
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item
              name="name"
              label="Name"
              rules={[{ required: true, message: "Please enter pet name" }]}
            >
              <Input placeholder="Please enter pet name" />
            </Form.Item>
          </Col>
        </Row>
        <Row gutter={16}>
        <Col span={12}>
            <Form.Item
              name="type"
              label="Type"
              rules={[{ required: true, message: "Please enter pet type" }]}
            >
              <Select
                placeholder="Select a person"
              >
                <Option value="dog">Dog</Option>
                <Option value="cat">Cat</Option>
                <Option value="other">Other</Option>
              </Select>
              </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item
              name="breed"
              label="Breed"
              rules={[{ required: true, message: "Please enter pet breed" }]}
            >
              <Input placeholder="Please enter pet breed" />
            </Form.Item>
          </Col>
        </Row>
        <Row gutter={16}>
        <Col span={12}>
            <Form.Item
              name="color"
              label="Color"
              rules={[{ required: true, message: "Please enter pet color" }]}
            >
              <Input placeholder="Please enter pet color" />
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item
              name="gender"
              label="Gender"
              rules={[{ required: true, message: "Please enter pet gender" }]}
            >
              <Select placeholder="Please select a gender">
                <Option value="MALE">MALE</Option>
                <Option value="FEMALE">FEMALE</Option>
              </Select>
            </Form.Item>
          </Col>
        </Row>
        <Row gutter={16}>
        <Col span={12}>
            <Form.Item
              name="weight"
              label="Weight"
              rules={[{ required: true, message: "Please enter pet weight" }]}
            >
              <Input placeholder="Please enter pet weight" />
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item
              name="dob"
              label="Date of Birth"
              rules={[{ required: true, message: "Please enter pet weight" }]}
            >
              <DatePicker onChange={onChangeDate} />
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item
              name="vaccines"
              label="Vaccines"
              rules={[{ required: true, message: "Please enter pet vaccines" }]}
            >
              <Input placeholder="Please enter pet vaccines" />
            </Form.Item>
          </Col>
        </Row>
        <Row gutter={16}>
          <Col span={24}>
            <Form.Item
              name="notes"
              label="Notes"
              rules={[{ message: "Please enter pet weight" }]}
            >
              <Input.TextArea placeholder="Please enter pet weight" />
            </Form.Item>
          </Col>
        </Row>
        <Row>
          <Col span={12}>
            <Form.Item>
              <Button type="default" htmlType="submit">
                Submit
              </Button>
            </Form.Item>
          </Col>
        </Row>
        <Row>{submitting && <Spin indicator={antIcon} />}</Row></>
      }
      </Form>
    </Drawer>
  );
}

export default AddNewDogForm;
