import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './fim-cust-history.reducer';

export const FimCustHistoryDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const fimCustHistoryEntity = useAppSelector(state => state.fim.fimCustHistory.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="fimCustHistoryDetailsHeading">
          <Translate contentKey="fimApp.fimFimCustHistory.detail.title">FimCustHistory</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{fimCustHistoryEntity.id}</dd>
          <dt>
            <span id="custId">
              <Translate contentKey="fimApp.fimFimCustHistory.custId">Cust Id</Translate>
            </span>
          </dt>
          <dd>{fimCustHistoryEntity.custId}</dd>
          <dt>
            <span id="historyTs">
              <Translate contentKey="fimApp.fimFimCustHistory.historyTs">History Ts</Translate>
            </span>
          </dt>
          <dd>
            {fimCustHistoryEntity.historyTs ? (
              <TextFormat value={fimCustHistoryEntity.historyTs} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="clientId">
              <Translate contentKey="fimApp.fimFimCustHistory.clientId">Client Id</Translate>
            </span>
          </dt>
          <dd>{fimCustHistoryEntity.clientId}</dd>
          <dt>
            <span id="idType">
              <Translate contentKey="fimApp.fimFimCustHistory.idType">Id Type</Translate>
            </span>
          </dt>
          <dd>{fimCustHistoryEntity.idType}</dd>
          <dt>
            <span id="ctryCode">
              <Translate contentKey="fimApp.fimFimCustHistory.ctryCode">Ctry Code</Translate>
            </span>
          </dt>
          <dd>{fimCustHistoryEntity.ctryCode}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="fimApp.fimFimCustHistory.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{fimCustHistoryEntity.createdBy}</dd>
          <dt>
            <span id="createdTs">
              <Translate contentKey="fimApp.fimFimCustHistory.createdTs">Created Ts</Translate>
            </span>
          </dt>
          <dd>
            {fimCustHistoryEntity.createdTs ? (
              <TextFormat value={fimCustHistoryEntity.createdTs} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="fimApp.fimFimCustHistory.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{fimCustHistoryEntity.updatedBy}</dd>
          <dt>
            <span id="updatedTs">
              <Translate contentKey="fimApp.fimFimCustHistory.updatedTs">Updated Ts</Translate>
            </span>
          </dt>
          <dd>
            {fimCustHistoryEntity.updatedTs ? (
              <TextFormat value={fimCustHistoryEntity.updatedTs} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="recordStatus">
              <Translate contentKey="fimApp.fimFimCustHistory.recordStatus">Record Status</Translate>
            </span>
          </dt>
          <dd>{fimCustHistoryEntity.recordStatus}</dd>
        </dl>
        <Button tag={Link} to="/fim/fim-cust-history" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/fim/fim-cust-history/${fimCustHistoryEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default FimCustHistoryDetail;
