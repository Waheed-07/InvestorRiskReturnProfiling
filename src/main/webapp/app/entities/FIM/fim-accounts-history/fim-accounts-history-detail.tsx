import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './fim-accounts-history.reducer';

export const FimAccountsHistoryDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const fimAccountsHistoryEntity = useAppSelector(state => state.fim.fimAccountsHistory.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="fimAccountsHistoryDetailsHeading">
          <Translate contentKey="fimApp.fimFimAccountsHistory.detail.title">FimAccountsHistory</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{fimAccountsHistoryEntity.id}</dd>
          <dt>
            <span id="accountId">
              <Translate contentKey="fimApp.fimFimAccountsHistory.accountId">Account Id</Translate>
            </span>
          </dt>
          <dd>{fimAccountsHistoryEntity.accountId}</dd>
          <dt>
            <span id="historyTs">
              <Translate contentKey="fimApp.fimFimAccountsHistory.historyTs">History Ts</Translate>
            </span>
          </dt>
          <dd>
            {fimAccountsHistoryEntity.historyTs ? (
              <TextFormat value={fimAccountsHistoryEntity.historyTs} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="custId">
              <Translate contentKey="fimApp.fimFimAccountsHistory.custId">Cust Id</Translate>
            </span>
          </dt>
          <dd>{fimAccountsHistoryEntity.custId}</dd>
          <dt>
            <span id="relnId">
              <Translate contentKey="fimApp.fimFimAccountsHistory.relnId">Reln Id</Translate>
            </span>
          </dt>
          <dd>{fimAccountsHistoryEntity.relnId}</dd>
          <dt>
            <span id="relnType">
              <Translate contentKey="fimApp.fimFimAccountsHistory.relnType">Reln Type</Translate>
            </span>
          </dt>
          <dd>{fimAccountsHistoryEntity.relnType}</dd>
          <dt>
            <span id="operInst">
              <Translate contentKey="fimApp.fimFimAccountsHistory.operInst">Oper Inst</Translate>
            </span>
          </dt>
          <dd>{fimAccountsHistoryEntity.operInst}</dd>
          <dt>
            <span id="isAcctNbr">
              <Translate contentKey="fimApp.fimFimAccountsHistory.isAcctNbr">Is Acct Nbr</Translate>
            </span>
          </dt>
          <dd>{fimAccountsHistoryEntity.isAcctNbr}</dd>
          <dt>
            <span id="bndAcctNbr">
              <Translate contentKey="fimApp.fimFimAccountsHistory.bndAcctNbr">Bnd Acct Nbr</Translate>
            </span>
          </dt>
          <dd>{fimAccountsHistoryEntity.bndAcctNbr}</dd>
          <dt>
            <span id="closingId">
              <Translate contentKey="fimApp.fimFimAccountsHistory.closingId">Closing Id</Translate>
            </span>
          </dt>
          <dd>{fimAccountsHistoryEntity.closingId}</dd>
          <dt>
            <span id="subSegment">
              <Translate contentKey="fimApp.fimFimAccountsHistory.subSegment">Sub Segment</Translate>
            </span>
          </dt>
          <dd>{fimAccountsHistoryEntity.subSegment}</dd>
          <dt>
            <span id="branchCode">
              <Translate contentKey="fimApp.fimFimAccountsHistory.branchCode">Branch Code</Translate>
            </span>
          </dt>
          <dd>{fimAccountsHistoryEntity.branchCode}</dd>
          <dt>
            <span id="acctStatus">
              <Translate contentKey="fimApp.fimFimAccountsHistory.acctStatus">Acct Status</Translate>
            </span>
          </dt>
          <dd>{fimAccountsHistoryEntity.acctStatus}</dd>
          <dt>
            <span id="ctryCode">
              <Translate contentKey="fimApp.fimFimAccountsHistory.ctryCode">Ctry Code</Translate>
            </span>
          </dt>
          <dd>{fimAccountsHistoryEntity.ctryCode}</dd>
          <dt>
            <span id="acctOwners">
              <Translate contentKey="fimApp.fimFimAccountsHistory.acctOwners">Acct Owners</Translate>
            </span>
          </dt>
          <dd>{fimAccountsHistoryEntity.acctOwners}</dd>
          <dt>
            <span id="remarks">
              <Translate contentKey="fimApp.fimFimAccountsHistory.remarks">Remarks</Translate>
            </span>
          </dt>
          <dd>{fimAccountsHistoryEntity.remarks}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="fimApp.fimFimAccountsHistory.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{fimAccountsHistoryEntity.createdBy}</dd>
          <dt>
            <span id="createdTs">
              <Translate contentKey="fimApp.fimFimAccountsHistory.createdTs">Created Ts</Translate>
            </span>
          </dt>
          <dd>
            {fimAccountsHistoryEntity.createdTs ? (
              <TextFormat value={fimAccountsHistoryEntity.createdTs} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="fimApp.fimFimAccountsHistory.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{fimAccountsHistoryEntity.updatedBy}</dd>
          <dt>
            <span id="updatedTs">
              <Translate contentKey="fimApp.fimFimAccountsHistory.updatedTs">Updated Ts</Translate>
            </span>
          </dt>
          <dd>
            {fimAccountsHistoryEntity.updatedTs ? (
              <TextFormat value={fimAccountsHistoryEntity.updatedTs} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="recordStatus">
              <Translate contentKey="fimApp.fimFimAccountsHistory.recordStatus">Record Status</Translate>
            </span>
          </dt>
          <dd>{fimAccountsHistoryEntity.recordStatus}</dd>
        </dl>
        <Button tag={Link} to="/fim/fim-accounts-history" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/fim/fim-accounts-history/${fimAccountsHistoryEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default FimAccountsHistoryDetail;
